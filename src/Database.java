import java.io.*;
import java.util.*;

public class Database {
	StudentFactory studentFactory;
	ArrayList<Student> students = new ArrayList<Student>();
	HashMap<Integer, Student> serieBuletinStudent = new HashMap<Integer, Student>();
	HashMap<String, Student> nameStudent = new HashMap<String, Student>();

	public String[] breakLine(String line) {
		String[] studentInfo = new String[3];
		studentInfo = line.split(" ");
		return studentInfo;
	}

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

	public boolean search(Student s) {
		for (Student st : students) {
			if (st.equals(s)) {
				return true;
			}
		}
		return false;
	}

	public Student searchByCardIdentity(Integer cardIdentity) {
		return serieBuletinStudent.get(cardIdentity);
	}

	public Student searchByFirstNameLastName(String firstName, String lastName) {
		String string = firstName.concat(lastName);
		Student s = nameStudent.get(string);
		if (s == null){
			System.out.println("Not found");
		}
		return s;
	}

	public void add(Student s) {
		if (students.contains(s)){
			System.out.println("This student is already in the database");
			return;
		}
		students.add(s);
		serieBuletinStudent.put(s.serieBuletin, s);
		String string = s.firstName.concat(s.lastName);
		nameStudent.put(string, s);
	}

	public void addAtIndex(int index, Student s) {
		students.add(index, s);
		serieBuletinStudent.put(s.serieBuletin, s);
		String string = s.firstName.concat(s.lastName);
		nameStudent.put(string, s);
	}

	public void addStudent(String studentType, String firstName,
			String lastName, int serieBuletin, int salariu)
			throws NumberFormatException, NotAStudent, IncorrectName {
		Student student = studentFactory.createStudent(studentType, firstName,
				lastName, serieBuletin, salariu);
		this.add(student);
	}

	public void remove(String firstName, String lastName) {
		Student s = searchByFirstNameLastName(firstName, lastName);
		if (!students.remove(s)){
			System.out.println("Student doesn't exist!");
			return;
		}
		serieBuletinStudent.remove(s.serieBuletin);
		nameStudent.remove(s);
	}

	public void editFirstName(String firstName, String lastName, String newName) throws NumberFormatException, NotAStudent, IncorrectName {
		Student s = searchByFirstNameLastName(firstName, lastName);
		if (s==null){
			return;
		}
		int index = students.indexOf(s);
		Student newStudent = studentFactory.createStudent(s.getType(), newName,
				s.lastName, s.serieBuletin, s.salariu);
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
