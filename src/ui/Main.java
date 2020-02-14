package ui;

import exceptions.IncompleteInformationException;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotRegisteredYet;
import model.*;

public class Main {

	public static void main(String[] args) {

		Company c = new Company();
		System.out.println(c.getRegisteredUsers().size());


		try {
			c.addRegisteredUser(55353, "andrea", "nuñez", 322, "fajkjfakj", DocumentType.CITIZENSHIP_CARD,"kflkfsñ");
			
			c.toGiveATurnToAnUser("andrea");
			System.out.println(c.toSearchUser("andrea").getName());
			
			c.addRegisteredUser(58985, "yury", "borrero", 4444, "sjksj", DocumentType.CITIZENSHIP_CARD, "ajfkfj1");
			System.out.println(c.getRegisteredUsers().size());
			System.out.println(c.toSearchUser("yury").getName());
			c.toGiveATurnToAnUser("yury");
			c.addRegisteredUser(589851, "yury1", "borrero1", 44441, "sjksj1", DocumentType.CITIZENSHIP_CARD, "ajfkfj1");
//			c.addRegisteredUser(589852, "yury2", "borrero2", 44442, "sjksj2", DocumentType.CITIZENSHIP_CARD, "ajfkfj2");
//			c.addRegisteredUser(589853, "yury3", "borrero3", 44443, "sjksj3", DocumentType.CITIZENSHIP_CARD, "ajfkfj3");
//			c.addRegisteredUser(589854, "yury4", "borrero4", 44444, "sjksj4", DocumentType.CITIZENSHIP_CARD, "ajfkfj4");
//			c.addRegisteredUser(589855, "yury5", "borrero5", 44445, "sjksj5", DocumentType.CITIZENSHIP_CARD, "ajfkfj5");
//			c.addRegisteredUser(589856, "yury6", "borrero6", 44446, "sjksj6", DocumentType.CITIZENSHIP_CARD, "ajfkfj6");
//			c.addRegisteredUser(589857, "yury7", "borrero7", 44447, "sjksj7", DocumentType.CITIZENSHIP_CARD, "ajfkfj7");
//			c.addRegisteredUser(589858, "yury8", "borrero8", 44448, "sjksj8", DocumentType.CITIZENSHIP_CARD, "ajfkfj8");
//			c.addRegisteredUser(589859, "yury9", "borrero9", 44449, "sjksj9", DocumentType.CITIZENSHIP_CARD, "ajfkfj9");
//			c.addRegisteredUser(5898510, "yury10", "borrero10", 444410, "sjksj10", DocumentType.CITIZENSHIP_CARD, "ajfkfj10");
//			c.addRegisteredUser(5898511, "yury11", "borrero11", 444411, "sjksj11", DocumentType.CITIZENSHIP_CARD, "ajfkfj11");
//			c.addRegisteredUser(5898512, "yury12", "borrero12", 444412, "sjksj12", DocumentType.CITIZENSHIP_CARD, "ajfkfj12");
//			c.addRegisteredUser(5898513, "yury13", "borrero13", 444413, "sjksj13", DocumentType.CITIZENSHIP_CARD, "ajfkfj13");
//			c.addRegisteredUser(5898514, "yury14", "borrero14", 444414, "sjksj14", DocumentType.CITIZENSHIP_CARD, "ajfkfj14");
//			c.addRegisteredUser(5898515, "yury15", "borrero15", 444415, "sjksj15", DocumentType.CITIZENSHIP_CARD, "ajfkfj15");

			c.toGiveATurnToAnUser("yury1");
			//juju
			//ysjhdsjds

		} catch (UserAlreadyExistsException | IncompleteInformationException e) {
			e.printStackTrace();
		}catch(UserNotRegisteredYet e) {
			e.printStackTrace();
		}
		
	}
}
