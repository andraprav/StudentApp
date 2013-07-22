public class StudentFactory {
	private static final StudentFactory factory = new StudentFactory();

	public enum StudentType {
		Undergraduate, Graduate, PartTime
	}

	private StudentFactory() {
	};

	public static StudentFactory getInstance() {
		return factory;
	}

	public Student createStudent(String studentType, String firstName,
			String lastName, int serieBuletin, int salariu)  throws NotAStudent,IncorrectName {
		switch (studentType) {
		case "Undergraduate":
			return new Undergraduate(firstName, lastName, serieBuletin, salariu);
		case "Graduate":
			return new Graduate(firstName, lastName, serieBuletin, salariu);
		case "PartTime":
			return new PartTime(firstName, lastName, serieBuletin, salariu);
		}
		throw new NotAStudent("The student type " + studentType
				+ " is not recognized.");
	}

}
