public class PartTime extends Student {

	@Override
	public int getHoursPerWeek() {
		return 20;
	}

	public PartTime(String firstName, String lastName, int serieBuletin, int salariu) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.serieBuletin = serieBuletin;
		this.salariu = salariu;
	}

	@Override
	public String getType() {
		return "PartTime";
	}

}
