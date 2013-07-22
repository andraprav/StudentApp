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

	public int getSerieBuletin() {
		return serieBuletin;
	}

	public int getSalariu() {
		return salariu;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Student: " + firstName + ", " + lastName + ", " + serieBuletin
				+ ", " + salariu;
	}

	public void setSerieBuletin(int serieBuletin) {
		this.serieBuletin = serieBuletin;
	}

	public void setSalariu(int salariu) {
		this.salariu = salariu;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + salariu;
		result = prime * result + serieBuletin;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (salariu != other.salariu)
			return false;
		if (serieBuletin != other.serieBuletin)
			return false;
		return true;
	}

}
