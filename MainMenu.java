package phoneBook;

import java.awt.SystemTray;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import phoneBook.Contact;
import phoneBook.Person;
import phoneBook.Address;

public class MainMenu {
	
	public static void main(String[] args) {

		//Starting contacts
		Person p1 = new Person("Robert", "", "Bray");
		Address a1 = new Address("123 Blank Street", "Somewhere", "MO", "65542", "8162099202");
		Person p2 = new Person("Margaret", "B", "Addams");
		Address a2 = new Address("321 Road", "Anywhere", "AZ", "12345", "9786543201");
		Person p3 = new Person("Joe", "A", "Bishop");
		Address a3 = new Address("629 Galactic Highway", "Nowhere", "MN", "54321", "1230456789");
		Person p4 = new Person("Jeremy", "C", "Smithe");
		Address a4 = new Address("589 Main St.", "Saint Louis", "MO", "98765", "6490873215");
		
		//Skippy the magnificant, 123 goat street, skippi stan, me, 12345, 9870123456
		//(816) 209-9202
		
		//Set starting contacts to Contact array
		Contact person1 = new Contact(p1, a1);
		Contact.contactList[0] = person1;
		Contact person2 = new Contact(p2, a2);
		Contact.contactList[1] = person2;
		Contact person3 = new Contact(p3, a3);
		Contact.contactList[2] = person3;
		Contact person4 = new Contact(p4, a4);
		Contact.contactList[3] = person4;
				
		mainMenu();
	}
	public static void mainMenu() {

		System.out.println("Phonebook Menu Options");
		System.out.println("******************************************************");
		System.out.println("1: Add a new contact");
		System.out.println("2: Remove a contact");
		System.out.println("3: List all contacts alphabetically");
		System.out.println("4: Search for a contact");
		System.out.println("5: Edit a contact");
		System.out.println("6: Exit");
		System.out.println("******************************************************");

		System.out.println("Please select an option from the menu:\n");

		Scanner input = new Scanner(System.in);

		try {

			String temp = input.next();

			int menu = Integer.parseInt(temp);

			switch (menu) { // Methods I need to write

			case 1: // addContact(); check capitalization formatting
				System.out.println("1: Add a new contact\n");
				Contact.addContact();
				break;
			case 2: //can't remove a newly added contact; errors into a loop when search fails
				System.out.println("2: Remove a contact");
				Contact.removeContact();
				break;
			case 3:
				System.out.println("3: List contacts alphabetically");
				Contact.listContact();
				break;
			case 4: //prints 'null' in contacts that don't match search.  Functional, but not pretty
				System.out.println("4: Search for a contact");
				Contact.searchContact();
				break;
			case 5: 
				System.out.println("5: Edit a contact");
				Contact.editContact();
				break;
			case 6: 
				Contact.exitNow();
				break;
			default: //catches wrong number inputs
				System.out.println("default Invalid entry: Please choose an option \n");
				mainMenu();
				break;
			}

		} catch (NumberFormatException e) { //catches non-integer input
			System.out.println("catch Invalid entry, please choose number 1 - 6 \n");
			mainMenu();
		}
	}
}
