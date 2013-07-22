public abstract class Student {
	String firstName = "Prenume";
	String lastName = "Nume";
	int serieBuletin = 0;
	int salariu = 0;

	public abstract int getHoursPerWeek();
	public abstract String getType();
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}
