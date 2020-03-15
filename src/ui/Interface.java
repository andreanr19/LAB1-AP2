package ui;

import java.io.IOException;
import java.util.Scanner;

import exceptions.IncompleteInformationException;
import exceptions.TurnsTypeNonExistent;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;
import exceptions.UserNotRegisteredYet;
import model.Company;
import model.Date;
import model.DocumentType;
import model.TurnType;
import model.User;

public class Interface {

	private Scanner input;
	private Company c;
	Date tiempoactual;
	int horaactual;
	int minutoactual;
	int segundoactual;

	public Interface() {
		c = new Company("serializables/Serializable.dat");
		input = new Scanner(System.in);
		tiempoactual = c.getMyDate();
		horaactual = tiempoactual.getCurrentHour();
		minutoactual = tiempoactual.getCurrentMinute();
		segundoactual = tiempoactual.getCurrentSecond();
	}

	public static void main(String[] args) {

		try {
			Interface i = new Interface();
			i.showMenu();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	String hourUser = "";
	String incremento = "00:00:00";
	int incrementoHora = 0;
	int incrementoMinuto = 0;
	int incrementoSegundo = 0;
	int diferenciah = 0;
	int diferenciam = 0;
	int diferencias = 0;

	public void showMenu() {
		int read = 0;

		while (read != 11) {
			showOptions();
			read = input.nextInt();
			input.nextLine();

			switch (read) {
			case 1:
				boolean continueCicle1 = true;
				do {
					try {
						System.out.println("Enter the id of the user");
						int id = Integer.parseInt(input.nextLine());
						System.out.println("Enter the name of the user");
						String name = input.nextLine();
						System.out.println("Enter the last Name of the user");
						String lastName = input.nextLine();
						System.out.println("Enter the phone of the user");
						String phone = input.nextLine();
						System.out.println("Enter the address of the user");
						String address = input.nextLine();
						System.out.println("What's the type of document the user has?");
						System.out.println("1.CITIZENSHIP_CARD" + "\n" + "2.IDENTITY_CARD" + "\n"
								+ "3.CIVIL_REGISTRATION" + "\n" + "4.PASSPORT" + "\n" + "5.FOREIGNER_ID");
						String documentType = input.nextLine().toUpperCase();
						DocumentType dt = Enum.valueOf(DocumentType.class, documentType);
						System.out.println("Enter the document Number");
						String documentNumber = input.nextLine();
						c.addRegisteredUser(id, name, lastName, phone, address, dt, documentNumber);
						continueCicle1 = false;
					} catch (UserAlreadyExistsException e) {
						e.printStackTrace();
					} catch (IncompleteInformationException e) {
						e.printStackTrace();
					}
				} while (continueCicle1 == true);
				break;
			case 2:
				boolean continueCicle2 = true;
				do {
					try {
						System.out.println("Enter the name of the user you want to add a turn to");
						String nameUser = input.nextLine();
						System.out.println("Enter the document number of the user");
						String documentNumber = input.nextLine();

						// ----------------------------------------//
//						System.out.println("What type of turn is the user going to have?");
						System.out.println("These are the turn's types actually in the system");
						System.out.println(c.showTypesTurns());
						System.out.println(
								"Would you like to choose a type showered previously or add a new one? \n1.Existent types \n2.New type");
						String answer = input.nextLine();
						if (answer.equalsIgnoreCase("1")) {

							String theanswer = c.showTypesTurns();
							System.out.println(theanswer);
							if (!theanswer.equalsIgnoreCase("There are not types of turns yet, you should add one")) {
								int typeOfTurn = Integer.parseInt(input.nextLine());

								c.assignIdToTheTurnType();
								int sizeofturns = c.getTurnsTypes().size();

								do {
									for (int i = 0; i < c.getTurnsTypes().size(); i++) {
										if (typeOfTurn == c.getTurnsTypes().get(i).getId()) {
											TurnType nt = new TurnType(c.getTurnsTypes().get(i).getName(),
													c.getTurnsTypes().get(i).getDuration());
											c.toGiveATurnToAnUser3(nameUser, documentNumber, nt);
											continueCicle2 = false;
										}
									}
								} while (typeOfTurn > sizeofturns);
							}

						} else {
							System.out.println("Enter the name of the type of the turn");
							String nameOfTheTurn = input.nextLine();
							System.out.println("Enter the duration of the turn");
							float durationOfTheTurn = input.nextFloat();
							input.nextLine();
							TurnType nt = new TurnType(nameOfTheTurn, durationOfTheTurn);
							c.toAddATypeToATurn(nt);
							c.toGiveATurnToAnUser3(nameUser, documentNumber, nt);
							continueCicle2 = false;
						}

					} catch (UserNotRegisteredYet e) {
						e.printStackTrace();
					} catch (TurnsTypeNonExistent e) {
						e.printStackTrace();
					}
				} while (continueCicle2 == true);
				break;
			case 3:
				boolean continueCicle3 = true;
				do {
					try {
						User theUser = c.toreturnUserStatusFalse();
						System.out.println("The following turn to be attended is from the user " + theUser.getName());
						System.out.println("Was the person there at the moment of being called? " + "\n" + "1.YES"
								+ ("\n") + "2.NO");

						String answer = input.nextLine();
						if (answer.equalsIgnoreCase("1")) {
							c.toAttendAnUser(true);

						} else {
							c.toAttendAnUser(false);
						}
						continueCicle3 = false;
					} catch (Exception e) {
						e.printStackTrace();
					}

				} while (continueCicle3);
				break;
			case 4:
				boolean continueCicle4 = true;
				do {
					try {
						System.out.println("Enter the document number of the person you want to search for");
						String dn = input.nextLine();
						System.out.println(c.toSearchAnUser2(dn));
						continueCicle4 = false;
					} catch (Exception e) {
						e.printStackTrace();
					}
				} while (continueCicle4 == true);
				break;
			case 5:
				System.out.println(c.showActualDateAndHour());

				break;
			case 6:

				System.out.println(
						"Enter the hour you want to actualice the hour's system to int the following format: hour/minutes/seconds");
				String time = input.nextLine();
				c.actualiceSystemsHour(time);

				hourUser = c.actualiceSystemsHour2(time);
				incremento = time;

				int posicion = 0;
				posicion = incremento.indexOf(":");
				incrementoHora = Integer.parseInt(incremento.substring(0, posicion));
				incremento = incremento.substring(posicion + 1);
				posicion = incremento.indexOf(":");
				incrementoMinuto = Integer.parseInt(incremento.substring(0, posicion));
				incremento = incremento.substring(posicion + 1);
				incrementoSegundo = Integer.parseInt(incremento);

				diferenciah = incrementoHora - horaactual;
				diferenciam = incrementoMinuto - minutoactual;
				diferencias = incrementoSegundo - segundoactual;

				break;
			case 7:
				c.serializableUser();
				break;
			case 8:
				System.out.println("How many users do you want to generate");
				int cantidad= Integer.parseInt(input.nextLine());
				c.loadUsers(cantidad);
				break;
			case 9:
				try {
					System.out.println(
							"Do you want to generate a report from the console's system or in an archive?\n 1.Console's System\n 2.Archive");
					String answer = input.nextLine();

					if (answer.equalsIgnoreCase("1")) {
						System.out.println(c.toGenerateAReportConsole());
					} else {
						c.toGenerateReport("reports");
					}
				} catch (IOException | UserNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void showOptions() {
		System.out.println("Welcome, enter the option you want");
		System.out.println(c.showActualDateAndHour());
		System.out.println("---------------");
		System.out.println(hourUser);
		System.out.println("---------------");
//		System.out.println(c.actualiceSystemsHour2(incremento));
		System.out.println("1.To register a new User in the system");
		System.out.println("2.To add a turn to an user");
		System.out.println("3.To attend an user");
		System.out.println("4.To search for an especific user");
		System.out.println("5.To show the actual date and hour of the system");
		System.out.println("6.To actualice the actual the actual date and hour of the system ");
		System.out.println("7.To serialize information");
		System.out.println("8.To generate users");
		System.out.println("9.To generate report of the registered Users");

		System.out.println(
				(tiempoactual.getCurrentHour() + diferenciah) + ":" + (tiempoactual.getCurrentMinute() + diferenciam)
						+ ":" + (tiempoactual.getCurrentSecond() + diferencias));
	}

}
