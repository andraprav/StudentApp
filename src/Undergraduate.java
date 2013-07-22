public class Undergraduate extends Student {

	@Override
	public int getHoursPerWeek() {
		return 30;
	}

	public Undergraduate(String firstName, String lastName, int serieBuletin, int salariu) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.serieBuletin = serieBuletin;
		this.salariu = salariu;
	}

	@Override
	public String getType() {
		return "Undergraduate";
	}

}
