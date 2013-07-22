import java.io.*;
import java.util.*;

/**
 * 
 * @author apravai class that holds information about students
 */
public class Database {
	StudentFactory studentFactory;
	ArrayList<Student> students = new ArrayList<Student>();
	HashMap<Integer, Student> serieBuletinStudent = new HashMap<Integer, Student>();
	HashMap<String, Student> nameStudent = new HashMap<String, Student>();

	/**
	 * breaks a line into strings
	 * 
	 * @param line
	 *            - the line to be broken intro strings
	 * @return the array of strings
	 */
	public String[] breakLine(String line) {
		String[] studentInfo = new String[3];
		studentInfo = line.split(" ");
		return studentInfo;
	}

	/**
	 * constructor -opens the file from where to get the data -reads the data
	 * and stores it into different structures -closes the file
	 * 
	 * @throws FileNotFoundException
	 * @throws IncorrectName
	 * @throws NotAStudent
	 * @throws IOException
	 */
	public Database() throws FileNotFoundException, IncorrectName, NotAStudent,
			IOException {
		studentFactory = StudentFactory.getInstance();
		BufferedReader in = null;
		String line;
		String[] studentInfo;
		// opening the file
		in = new BufferedReader(new FileReader("Database"));

		// reading from file...
		while ((line = in.readLine()) != null) {
			studentInfo = breakLine(line);

			this.add(studentFactory.createStudent(studentInfo[0],
					studentInfo[1], studentInfo[2],
					Integer.parseInt(studentInfo[3]),
					Integer.parseInt(studentInfo[4])));

		}
		in.close();
	}

	/**
	 * 
	 * @param s
	 *            - the student to be searched for
	 * @return true or false if the student is or not in the database
	 */
	public boolean search(Student s) {
		for (Student st : students) {
			if (st.equals(s)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param cardIdentity
	 *            -card identity series
	 * @return the student's info
	 */
	public Student searchByCardIdentity(Integer cardIdentity) {
		return serieBuletinStudent.get(cardIdentity);
	}

	/**
	 * 
	 * @param firstName
	 *            - the first name of the student
	 * @param lastName
	 *            - the last name of the student
	 * @return the student's info
	 */
	public Student searchByFirstNameLastName(String firstName, String lastName) {
		String string = firstName.concat(lastName);
		Student s = nameStudent.get(string);
		if (s == null) {
			System.out.println("Not found");
		}
		return s;
	}

	/**
	 * 
	 * @param s
	 *            adds the student given in the database
	 */
	public void add(Student s) {
		if (students.contains(s)) {
			System.out.println("This student is already in the database");
			return;
		}
		students.add(s);
		serieBuletinStudent.put(s.serieBuletin, s);
		String string = s.firstName.concat(s.lastName);
		nameStudent.put(string, s);
	}

	/**
	 * adds a student at a specific index
	 * 
	 * @param index
	 * @param s
	 */
	public void addAtIndex(int index, Student s) {
		students.add(index, s);
		serieBuletinStudent.put(s.serieBuletin, s);
		String string = s.firstName.concat(s.lastName);
		nameStudent.put(string, s);
	}

	/**
	 * creates and adds a student in the database
	 * 
	 * @param studentType
	 * @param firstName
	 * @param lastName
	 * @param serieBuletin
	 * @param salariu
	 * @throws NumberFormatException
	 * @throws NotAStudent
	 * @throws IncorrectName
	 */
	public void addStudent(String studentType, String firstName,
			String lastName, int serieBuletin, int salariu)
			throws NumberFormatException, NotAStudent, IncorrectName {
		Student student = studentFactory.createStudent(studentType, firstName,
				lastName, serieBuletin, salariu);
		this.add(student);
	}

	/**
	 * removes a student form the database
	 * 
	 * @param firstName
	 * @param lastName
	 */
	public void remove(String firstName, String lastName) {
		String string = firstName.concat(lastName);
		Student s = searchByFirstNameLastName(firstName, lastName);
		if (!students.remove(s)) {
			System.out.println("Student doesn't exist!");
			return;
		}
		serieBuletinStudent.remove(s.serieBuletin);
		nameStudent.remove(string);
	}

	/**
	 * removes student s from database
	 * 
	 * @param s
	 */
	public void remove(Student s) {
		String string = s.firstName.concat(s.lastName);
		if (!students.remove(s)) {
			System.out.println("Student doesn't exist!");
			return;
		}
		serieBuletinStudent.remove(s.serieBuletin);
		nameStudent.remove(string);
	}

	/**
	 * edits one member of student info
	 * @param firstName students name
	 * @param lastName students name
	 * @param member what member would you like to edit
	 * @param newMember the new member that you would like to add
	 * @throws NumberFormatException
	 * @throws NotAStudent
	 * @throws IncorrectName
	 */
	public void editInfo(String firstName, String lastName, String member,
			String newMember) throws NumberFormatException, NotAStudent,
			IncorrectName {
		Student s = searchByFirstNameLastName(firstName, lastName);
		Student newStudent;
		if (s == null) {
			return;
		}
		int index = students.indexOf(s);
		switch (member) {
		case "firstName":
			newStudent = studentFactory.createStudent(s.getType(), newMember,
					s.lastName, s.serieBuletin, s.salariu);
			break;
		case "lastName":
			newStudent = studentFactory.createStudent(s.getType(), s.firstName,
					newMember, s.serieBuletin, s.salariu);
			break;
		case "serieBuletin":
			newStudent = studentFactory.createStudent(s.getType(), s.firstName,
					s.lastName, Integer.parseInt(newMember), s.salariu);
			break;
		case "salariu":
			newStudent = studentFactory.createStudent(s.getType(), s.firstName,
					s.lastName, s.serieBuletin, Integer.parseInt(newMember));
			break;
		default:
			System.out.println(member + " is not a member");
			return;

		}
		remove(s);
		addAtIndex(index, newStudent);
	}
	
	@Override
	public String toString() {
		String s = "";
		for (int studentCounter = 0; studentCounter < students.size(); studentCounter++) {
			s += students.get(studentCounter) + "\n";
		}
		return s;
	}

}
