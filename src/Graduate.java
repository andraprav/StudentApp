public class Graduate extends Student {

	@Override
	public int getHoursPerWeek() {
		return 35;
	}

	public Graduate(String firstName, String lastName, int serieBuletin, int salariu) throws IncorrectName{
		this.firstName = firstName;
		this.lastName = lastName;
		this.serieBuletin = serieBuletin;
		this.salariu = salariu;
	}

	@Override
	public String getType() {
		return "Graduate";
	}

}
