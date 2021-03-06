package phoneBook;

import java.util.Arrays;
import java.util.Scanner;

public class Contact {

	private Person newPerson;
	private Address newAddress;

	public static Contact[] contactList = new Contact[4];

	public Contact(Person newPerson, Address newAddress) {
		setPerson((Person) newPerson);
		setAddress((Address) newAddress);
	}

	public Person getPerson() {
		return newPerson;
	}

	public void setPerson(Person person) {
		this.newPerson = person;
	}

	public Address getAddress() {
		return newAddress;
	}

	public void setAddress(Address address) {
		this.newAddress = address;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void addContact() { // case 1

		Person newPerson = new Person(null, null, null);
		Address newAddress = new Address(null, null, null, null, null);

		Scanner input = new Scanner(System.in);

		System.out.println(
				"Please enter a new contact, seperating each value by a comma. \nYou may copy and paste a pre-formatted value.");
		System.out.println("Full Name, Street address, City, State, Zip code, Phone number \n");

		String tempNewCont = input.nextLine();
		tempNewCont.toUpperCase();

		String[] contactArray = tempNewCont.split(",");
		String tempFullName = contactArray[0].trim();
		String tempStreetAdd = contactArray[1].trim();
		String tempCity = contactArray[2].trim();
		String tempState = contactArray[3].trim();
		String tempZipCode = contactArray[4].trim();
		String tempPhoneNumber = contactArray[5].trim();

		if (contactArray.length != 6) {
			System.out.println("Incorrect Format");
			addContact(); 
		}
// format name
		String[] nameSplit = tempFullName.split(" ");
		for (int i = 0; i < nameSplit.length; i++) {
			nameSplit[i] = nameSplit[i].charAt(0) + nameSplit[i].substring(1).toLowerCase();
		}

		if (nameSplit.length > 2) {
			String firstName = nameSplit[0].trim();
			String lastName = nameSplit[(nameSplit.length) - 1].trim();
			String middleName = nameSplit[1].trim();
			newPerson.setFirstName(firstName);
			newPerson.setMiddleName(middleName);
			newPerson.setLastName(lastName);

		} else {
			String firstName = nameSplit[0].trim();
			String lastName = nameSplit[1].trim();
			newPerson.setFirstName(firstName);
			newPerson.setLastName(lastName);
		}

		String f = String.join(" ", nameSplit);
		String fullName = f;
		newPerson.setFullName(fullName);

// format street address
		String[] addressSplit = tempStreetAdd.split(" ");
		for (int i = 0; i < addressSplit.length; i++) {
			addressSplit[i] = addressSplit[i].charAt(0) + addressSplit[i].substring(1).toLowerCase();
		}
		String s = String.join(" ", addressSplit);

		String address = s;
		newAddress.setStreetAdd(address);

// format city
		String[] citySplit = tempCity.split(" ");
		for (int i = 0; i < citySplit.length; i++) {
			citySplit[i] = citySplit[i].charAt(0) + citySplit[i].substring(1).toLowerCase();
		}
		String c = String.join(" ", citySplit);

		String city = c;
		newAddress.setCity(city);

// format state
		if (tempState.length() == 2 && !tempState.matches("(0|[1-9]\\d*)")) {
			String state = tempState.toUpperCase();
			newAddress.setState(state);
		} else {
			System.out.println("Error: Please use the 2 letter state abbreviation in your entry.");
		}

// format zipCode
		if (tempZipCode.length() == 5 && tempZipCode.matches("(0|[1-9]\\d*)")) {
			String zipCode = tempZipCode;
			newAddress.setZipCode(zipCode);
		} else {
			System.out.println("Error: Please use a 5 digit zipcode with no punctuation");
		}

// format phoneNumber
		if (tempPhoneNumber.length() == 10 && tempPhoneNumber.matches("(0|[1-9]\\d*)")) {
			String phoneNumber = tempPhoneNumber;
			newAddress.setPhoneNumber(phoneNumber);
		} else {
			String phoneError = "Error: Please use a 10 digit phone number with no punctuation in your entry";
			System.out.println(phoneError);
		}

		int sizeUp = contactList.length + 1;

		int i = 0;

		Contact[] temp = new Contact[sizeUp];
		while (i < temp.length - 1) {
			temp[i] = contactList[i];
			i++;
		}

		contactList = temp;

		Contact newContact = new Contact(newPerson, newAddress);

		contactList[i] = newContact;

		// Print new array and confirm it has been added to the phone book.
		System.out.println(newContact.toString() + "\n ***The entry has been added to the phone book***\n \n ***Current Contacts*** \n");

		// prints all contacts
		for (Contact contact : contactList) {
			System.out.println(contact);
		}
		System.out.println("\n");
		continueUsing();
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void continueUsing() { 
		Scanner input = new Scanner(System.in);
		System.out.println("Would you like to continue using the phonebook? Y/N");

		String cont = input.nextLine();
		if (cont.equalsIgnoreCase("y")) { // later add a catch so it works when "yes" is typed out
			MainMenu.mainMenu();
		} else {
			exitNow();
		}
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void removeContact() { // case 2
		Scanner input = new Scanner(System.in);
		System.out.println("*****************************************"
				+ "\nPlease enter the phone number of the contact you would like to remove. (no spaces or punctuation)");

		String delete = input.next();

		Contact conDel = contactList[0];
		System.out.println("*****************************************");
		for (int i = 0; i < contactList.length; i++) {
			if (contactList[i] != null) {
				conDel = contactList[i];
				if (conDel.getAddress().getPhoneNumber().equals(delete)) {
					System.out.println(contactList[i]);
					System.out.println("Confirm deletion of the above contact: Y/N?");

					delete = input.next();

					if (delete.equalsIgnoreCase("y")) {
						contactList[i] = null;
						System.out.println("Contact Deleted");
					} else {
						System.out.println("Contact was not deleted.");
					}
				} else {
					System.out.println("No matching contacts");
				}

				continueUsing();
			}
		}
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void listContact() {

		int n = contactList.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (contactList[j].getPerson().getFirstName()
						.compareTo(contactList[j + 1].getPerson().getFirstName()) > 1) {
					Contact temp = contactList[j];
					contactList[j] = contactList[j + 1];
					contactList[j + 1] = temp;
				}
			}
		}

		for (Contact contact : contactList) {
			System.out.println(contact);
		}
			System.out.println("\n");
			continueUsing();
			
		}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void searchContact() { 

		System.out.println("******************************************************");
		System.out.println("Please select your search criteria.\nSearch By:");

		Scanner input = new Scanner(System.in);
		System.out.println("1: Name \n2: Street Address \n3: City \n4: State \n5: Zip Code \n6: Phone Number \n7: Exit Search");
		
		try {

			String temp = input.next();

			int menu = Integer.parseInt(temp);

			switch (menu) { 
			
			case 1:
								
				try {
					System.out.println("Which name you you like to use for your search? \n1: First name \n2: Middle name \n3: Last name \n4 Full name \n");
					String nameTemp = input.next();
					int subMenu = Integer.parseInt(nameTemp);
					switch (subMenu) {
					
					case 1:
						System.out.println("First Name: Enter your search");
						String firstSearch = input.next();
						
						for (Contact entry : contactList) {
							if (entry.getPerson().getFirstName().equalsIgnoreCase(firstSearch)) {
								System.out.println(entry);
							}else {
								System.out.println("No contacts match your search.");
							}
						}
						continueSearching();
						break;
					case 2: 
						System.out.println("Middle Name: Enter your search");
						String middleSearch = input.next();
						
						for (Contact entry : contactList) {
							if (entry.getPerson().getMiddleName().equalsIgnoreCase(middleSearch)) {
								System.out.println(entry);
								continueSearching();
							}else {
								System.out.println("No contacts match your search.");
							}
						}
						continueSearching();
						break;
					case 3:
						System.out.println("Last Name: Enter your search");
						String lastSearch = input.next();
						
						for (Contact entry : contactList) {
							if (entry.getPerson().getLastName().equalsIgnoreCase(lastSearch)) {
								System.out.println(entry);
								continueSearching();
							}else {
								System.out.println("No contacts match your search.");
							}
						}
						continueSearching();
						break;		
					case 4:
						System.out.println("Full Name: Enter your search");
						String fullSearch = input.next();
						
						for (Contact entry : contactList) {
							if (entry.getPerson().getFullName().equalsIgnoreCase(fullSearch)) {
								System.out.println(entry);
								continueSearching();
							}else {
								System.out.println("No contacts match your search.");
							}
						}
						continueSearching();
						break;	
					case 5: 
						MainMenu.mainMenu();
						break;
						
					default: //catches wrong number inputs
						System.out.println("default Invalid entry: Please choose an option \n");
						searchContact();
						break;
					}

				} catch (NumberFormatException e) { //catches non-integer input
					System.out.println("catch Invalid entry, please choose number 1 - 9 \n");
					searchContact();
				}

			case 2: 
				System.out.println("Street Address: Enter your search");
				String streetSearch = input.next();
				
				for (Contact entry : contactList) {
					if (entry.getAddress().getStreetAdd().equalsIgnoreCase(streetSearch)) {
						System.out.println(entry);
						continueSearching();
					}else {
						System.out.println("No contacts match your search.");
					}
				}
				continueSearching();
				break;
			case 3: 
				System.out.println("City: Enter your search");
				String citySearch = input.next();
				
				for (Contact entry : contactList) {
					if (entry.getAddress().getCity().equalsIgnoreCase(citySearch)) {
						System.out.println(entry);
						continueSearching();
					}else {
						System.out.println("No contacts match your search.");
					}
				}
				continueSearching();
				break;
			case 4: 
				System.out.println("State: Enter your search");
				String stateSearch = input.next();
				
				for (Contact entry : contactList) {
					if (entry.getAddress().getState().equalsIgnoreCase(stateSearch)) {
						System.out.println(entry);
						continueSearching();
					}else {
						System.out.println("No contacts match your search.");
					}
				}
				continueSearching();
				break;
			case 5: 
				System.out.println("Zip Code: Enter your search");
				String zipSearch = input.next();
				
				for (Contact entry : contactList) {
					if (entry.getAddress().getZipCode().equalsIgnoreCase(zipSearch)) {
						System.out.println(entry);
						continueSearching();
					}else {
						System.out.println("No contacts match your search.");
					}
				}
				continueSearching();
				break;
			case 6: 
				System.out.println("Phone Number: Enter your search");
				String phoneSearch = input.next();
				
				for (Contact entry : contactList) {
					if (entry.getAddress().getPhoneNumber().equalsIgnoreCase(phoneSearch)) {
						System.out.println(entry);
						continueSearching();
					}else {
						System.out.println("No contacts match your search.");
					}
				}
				continueSearching();
				break;
			case 7: 
				MainMenu.mainMenu();
				break;
				
			default: //catches wrong number inputs
				System.out.println("default Invalid entry: Please choose an option \n");
				searchContact();
				break;
			}

		} catch (NumberFormatException e) { //catches non-integer input
			System.out.println("catch Invalid entry, please choose number 1 - 7 \n");
			searchContact();
		}
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void continueSearching() { 
		Scanner input = new Scanner(System.in);
		System.out.println("Would you like to search again? Y/N");

		String cont = input.next();
		if (cont.equalsIgnoreCase("y")) { // later add a catch so it works when "yes" is typed out
			searchContact();
		} else {
			MainMenu.mainMenu();
		}
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void editContact() { // case 5

		Scanner input = new Scanner(System.in);

		System.out.println("*****************************************");
		System.out.print("Enter Contact Phone Number (with no punctuation): ");

		String update = input.next();

		System.out.println("*****************************************");

		Contact editContact = contactList[0];

		for (int i = 0; i < contactList.length; i++) {
			if (contactList[i] != null) {
				editContact = contactList[i];
				if (editContact.getAddress().getPhoneNumber().equals(update)) {

					System.out.println(editContact);
					System.out.println("What variable would you like to edit?");
					System.out.println("1: First Name" + "\n2: Middle Name" + "\n3: Last Name" + "\n4: Street Address"
							+ "\n5: City" + "\n6: State" + "\n7: Zip Code" + "\n8: Telephone Number"
							+ "\n9: Return to Main Menu");

					try {

						String temp = input.next();

						int edit = Integer.parseInt(temp);

						switch (edit) {

						case 1:
							System.out.print("*****************************************" + "\nEnter new first name: ");
							update = input.next();
							contactList[i].getPerson().setFirstName(update);
							System.out.println("Contact updated. New listing: \n" + contactList[i]);
							System.out.println("*****************************************");

							break;
						case 2:
							System.out.print("*****************************************" + "\nEnter new middle name: ");
							update = input.next();
							contactList[i].getPerson().setMiddleName(update);
							System.out.println("Contact updated. New listing: \n" + contactList[i]);
							System.out.println("*****************************************");
							break;
						case 3:
							System.out.print("*****************************************" + "\nEnter new last name: ");
							update = input.next();
							contactList[i].getPerson().setLastName(update);
							System.out.println("Contact updated. New listing: \n" + contactList[i]);
							System.out.println("*****************************************");
							break;
						case 4:
							System.out.print("*****************************************" + "\nEnter new street address: ");
							update = input.next();
							contactList[i].getAddress().setStreetAdd(update);
							System.out.println("Contact updated. New listing: \n" + contactList[i]);
							System.out.println("*****************************************");
							break;
						case 5:
							System.out.print("*****************************************" + "\nEnter new city: ");
							update = input.next();
							contactList[i].getAddress().setCity(update);
							System.out.println("Contact updated. New listing: \n" + contactList[i]);
							System.out.println("*****************************************");
							break;
						case 6:
							System.out.print("*****************************************" + "\nEnter new state: ");
							update = input.next();
							contactList[i].getAddress().setState(update);
							System.out.println("Contact updated. New listing: \n" + contactList[i]);
							System.out.println("*****************************************");
							break;
						case 7:
							System.out.print("*****************************************" + "\nEnter new zip code: ");
							update = input.next();
							contactList[i].getAddress().setZipCode(update);
							System.out.println("Contact updated. New listing: \n" + contactList[i]);
							System.out.println("*****************************************");
							break;
						case 8:
							System.out
									.print("*****************************************" + "\nEnter new phone number: ");
							update = input.nextLine();
							contactList[i].getAddress().setPhoneNumber(update);
							System.out.println("*****************************************");
							break;
						case 9:
							MainMenu.mainMenu();
							break;
						default:
							System.out.println("Error: Unreadable Input!");
							System.out.println("Would you like to edit again?");
							continueEditing();
							break;
						}
					} catch (NumberFormatException e) { // functional! catches non-integer entries
						System.out.println("catch Invalid entry, please choose number 1 - 9 \n");
						editContact();
					}
					continueEditing();
					break;
				}
			}
		}

	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void continueEditing() { 
		Scanner input = new Scanner(System.in);
		System.out.println("Would you like to edit again? Y/N");

		String cont = input.next();
		if (cont.equalsIgnoreCase("y")) { // later add a catch so it works when "yes" is typed out
			editContact();
		} else {
			MainMenu.mainMenu();
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void exitNow() { // case 6

		System.out.println("Thank you for using my phone book. Goodbye!");

	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public String toString() {
		return this.getPerson().getFirstName() + " " + this.getPerson().getMiddleName() + " "
				+ this.getPerson().getLastName() + ", " + this.getAddress().getStreetAdd() + ", "
				+ this.getAddress().getCity() + ", " + this.getAddress().getState() + ", "
				+ this.getAddress().getZipCode() + ", " + this.getAddress().getPhoneNumber();
	}

}
