package phoneBook;

public class Person{
	
	//properties
	protected String firstName;
	protected String middleName;
	protected String lastName;

	//parameterized constructor
	
	public Person(String firstName, String middleName, String lastName) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}
	public String getFullName() {
		return firstName + middleName + lastName;
	}
	
//	public String setFullName() {
//		this.fullName = fullName;
//	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Person firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + "]" + ", \n" + super.toString();
	}
	
}	