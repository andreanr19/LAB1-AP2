package ui;

import java.util.*;

import exceptions.IncompleteInformationException;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotRegisteredYet;
import model.*;

public class Interface {

	private Scanner input;
	private Company c;

	public Interface() {
		c = new Company();
		input = new Scanner(System.in);

	}

	public static void main(String[] args) {

		try{
			Interface i= new Interface();
			i.showMenu();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

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
						int phone = Integer.parseInt(input.nextLine());
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
						c.toGiveATurnToAnUser2(nameUser, documentNumber);
						continueCicle2 = false;

					} catch (UserNotRegisteredYet e) {
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
						if (answer == "1") {
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
				boolean continueCicle4=true;
				do {
					try {
						System.out.println("Enter the document number of the person you want to search for");
						String dn= input.nextLine();
						System.out.println(c.toSearchAnUser(dn));
						continueCicle4= false;
					}catch(Exception e) {
						e.printStackTrace();
					}
				}while(continueCicle4==true);
					
			}
		}
	}

	public void showOptions() {
		System.out.println("Welcome, enter the option you want");
		System.out.println("1.To register a new User in the system");
		System.out.println("2.To add a turn to an user");
		System.out.println("3.To attend an user");
		System.out.println("4.To search for an especific user");
	}

}
