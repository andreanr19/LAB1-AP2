package ui;
import java.util.*;
import model.*;
public class Main {

	public static void main(String[] args) throws Exception {

		Company c = new Company("serializables\\Sereliazable.dat");
		User andrea = new User(1010, "andrea", "nunez", "1010", "Diagonal", DocumentType.CITIZENSHIP_CARD, "1010138801");

		c.toAddATypeToATurn(new TurnType("ALMORZAR", 3.5f));
		c.addRegisteredUser(andrea.getId(), andrea.getName(), andrea.getLastName(), andrea.getPhone(),
				andrea.getAddress(), andrea.getDocumentType(), andrea.getDocumentNumber());
		c.toGiveATurnToAnUser3("andrea", "1010138801", new TurnType("ALMORZAR", 5.35f));
		
		c.addRegisteredUser(1313, "yury","borrero","2020","diagonal", DocumentType.CIVIL_REGISTRATION,"88888");
		c.toGiveATurnToAnUser3("yury", "88888", new TurnType("ALMORZAR", 5.35f));
		
		c.addRegisteredUser(1313, "Isabel","Nuñez","2020","diagonal", DocumentType.CIVIL_REGISTRATION,"2");
		c.toGiveATurnToAnUser3("Isabel", "2", new TurnType("Cenar", 5.35f));
		System.out.println(c.getRegisteredUsers().get(2).getTurnsUser().get(0).showMyDate());

		
		System.out.println(c.showActualDateAndHour());

		Scanner input = new Scanner(System.in);
		String dime= input.nextLine();
		System.out.println(c.getRegisteredUsers().get(2).getTurnsUser().get(0).showMyDate());

		
		System.out.println(c.showActualDateAndHour());
		System.out.println(c.getRegisteredUsers().get(2).getTurnsUser().get(0).showMyDate());
		
		System.out.println(c.showRegisteredUsers());
		
		c.orderUsersByBubbleSort();
		
		System.out.println(c.showRegisteredUsers());

		
		User f = c.serachByBinariMethodDocumentNumber("1010138801");
		System.out.println(f.getName());
		
		User a = c.serachByBinariMethodDocumentNumber("88888");
		System.out.println(a.getName());
		
		User u = c.serachByBinariMethodDocumentNumber("2");
		System.out.println(u.getName());
		
		System.out.println(c.showTypesTurns());
		


		System.out.println(c.getTurns().get(0).getTurnHour());
		System.out.println(c.getTurns().size());
		
	}

}
