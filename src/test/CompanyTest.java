package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.IncompleteInformationException;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;
import model.*;

class CompanyTest {

	public Company c;

	public void setUp1() {
		c = new Company();

	}

	public void setUp2() {
		try {
			c = new Company();

			c.addRegisteredUser(87878, "Andrea", "Nuñez", 78787, "Kra 9", DocumentType.CITIZENSHIP_CARD, "1010138801");
		} catch (UserAlreadyExistsException | IncompleteInformationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setUp3() {
		try {
			c = new Company();

			c.addRegisteredUser(87878, "Andrea", "Nuñez", 78787, "Kra 9", DocumentType.CITIZENSHIP_CARD, "1010138801");
			c.addRegisteredUser(58985, "yury", "borrero", 4444, "sjksj", DocumentType.CITIZENSHIP_CARD, "ajfkfj1");

		} catch (UserAlreadyExistsException | IncompleteInformationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setUp4() {
		try {
			c = new Company();
			int id = 0;
			String name = "A";
			String lastName = "B";
			int phone = 0;
			String address = "kra 1";
			DocumentType d = DocumentType.CITIZENSHIP_CARD;
			String documentNumber = "1";
			for (int i = 0; i < 400; i++) {
				c.addRegisteredUser(id, name, lastName, phone, address, d, documentNumber);
				c.toGiveATurnToAnUser2(name, documentNumber);

				id++;
				name += 1;
				lastName += 1;
				phone += 1;
				address += 1;
				d = d;
				documentNumber += 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	// si el usuario no existe y no hay usuarios en el programa
	void addRegisteredUserTest() {
		setUp1();
		try {
			c.addRegisteredUser(87878, "Andrea", "Nuñez", 78787, "Kra 9", DocumentType.CITIZENSHIP_CARD, "1010138801");
			User theUser = c.toSearchUserById("1010138801");

			assertEquals("Andrea", theUser.getName());
			assertEquals(theUser, c.getRegisteredUsers().get(0));
		} catch (UserAlreadyExistsException e) {
			e.printStackTrace();
		} catch (IncompleteInformationException e) {
			e.printStackTrace();
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}

		// si el usuario ya existe debe lanzar una excepcion
		assertThrows(UserAlreadyExistsException.class, () -> c.addRegisteredUser(87878, "Andrea", "Nuñez", 78787,
				"Kra 9", DocumentType.CITIZENSHIP_CARD, "1010138801"));
		// si hay usuarios en el programa
		try {
			c.addRegisteredUser(89899, "Yury", "Borrero", 6689, "Kra 20", DocumentType.FOREIGNER_ID, "99888765");
			assertEquals(c.toSearchUserById("99888765").getName(), "Yury");
		} catch (UserAlreadyExistsException | IncompleteInformationException | UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	// BUSCAR EL USUARIO CON BASE EN SU DOCUMENTO DE IDENTIDAD
	void toSearchUserByIdTest() {
		// si ya existe el usuario a buscar
		setUp2();
		boolean found = false;
		User userExpected = null;
		for (int i = 0; i < c.getRegisteredUsers().size() && !found; i++) {
			if (c.getRegisteredUsers().get(i).getDocumentNumber().equals("1010138801")) {
				userExpected = c.getRegisteredUsers().get(i);
				found = true;
			}
		}
		try {
			assertEquals(userExpected, c.toSearchUserById("1010138801"));
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}

		// si no existe el usuario a buscar
		assertThrows(UserNotFoundException.class, () -> c.toSearchUserById("555"));

		// si no hay usuarios en el programa
		try {
			setUp1();
			assertEquals(null, c.toSearchUserById("555"));
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}

		// si hay usuarios en el programa
		try {
			setUp2();
			assertEquals("1010138801", c.toSearchUserById("1010138801").getDocumentNumber());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void toGiveATurnToAnUser2Test() {
		try {
			setUp2();
			// i. Si el usuario tiene un turno activo, entonces debe probar que retorne el
			// turno activo y no genere otro
			c.toGiveATurnToAnUser2("Andrea", "1010138801");
			User theUser = c.toSearchUserById("1010138801");
			int expected = 1;
			int actual = theUser.getTurnsUser().size();
			assertEquals(expected, actual);

			// ii. Si el usuario no tiene un turno activo entonces le genera uno nuevo
			c.toAttendAnUser(true);
			c.toGiveATurnToAnUser2("Andrea", "1010138801");
			assertEquals(2, theUser.getTurnsUser().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void addTurnTest() {
		try {
			// Genera un nuevo turno= i. Consecutivo al último turno asignado
			setUp3();
			c.toGiveATurnToAnUser2("Andrea", "1010138801");
			c.toGiveATurnToAnUser2("yury", "ajfkfj1");

			String actual1 = c.getRegisteredUsers().get(0).getTurnsUser().get(0).getTurno();
			String expected1 = "A00";
			assertEquals(expected1, actual1);

			String actual2 = c.getRegisteredUsers().get(1).getTurnsUser().get(0).getTurno();
			String expected2 = "A01";
			assertEquals(expected2, actual2);

			// ii. Si es el primer turno genera el turno A00
			setUp1();
			c.addRegisteredUser(589851, "yury1", "borrero1", 44441, "sjksj1", DocumentType.CITIZENSHIP_CARD, "1");
			c.toGiveATurnToAnUser2("yury", "1");
			String expected = "A00";
			String actual = c.getTurns().get(0).getTurno();
			assertEquals(expected, actual);

			// iii. Si el último turno generado es el D99 entonces genera el turno E00
			setUp4();
			String before = c.getTurns().get(399).getTurno();
			c.addRegisteredUser(5898515, "yury15", "borrero15", 444415, "sjksj15", DocumentType.CITIZENSHIP_CARD, "15");
			c.toGiveATurnToAnUser2("yury15", "15");
			String after = c.getTurns().get(400).getTurno();

			boolean bf = false;
			if (before == "D99") {
				bf = true;
			}
			boolean af = false;
			if (after == "E00") {
				af = true;
			}
			assertEquals(bf, af);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void toAttendAnUserTest() {
		try {
			setUp3();
			c.toGiveATurnToAnUser2("Andrea", "1010138801");
			c.toGiveATurnToAnUser2("yury", "ajfkfj1");
			
			c.toAttendAnUser(true);
			boolean status=c.getRegisteredUsers().get(0).getTurnsUser().get(0).getStatus();
			assertEquals(false, status);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
