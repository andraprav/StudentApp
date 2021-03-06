import java.io.*;
import java.util.*;

public class Test {

	/**
	 * @param args
	 */
	public static String[] breakLine(String line) {
		String[] studentInfo = new String[3];
		studentInfo = line.split(" ");
		return studentInfo;
	}

	// method without Database class
	public static void test() throws NotAStudent, IncorrectName,
			NumberFormatException, FileNotFoundException, IOException {
		StudentFactory studentFactory = StudentFactory.getInstance();
		BufferedReader in = null;
		String line;
		String[] studentInfo;
		ArrayList<Student> students = new ArrayList<Student>();
		boolean found = false;
		Student myExampleStudent;
		// opening the file
		in = new BufferedReader(new FileReader("Database"));

		// reading from file...
		while ((line = in.readLine()) != null) {
			studentInfo = breakLine(line);

			students.add(studentFactory.createStudent(studentInfo[0],
					studentInfo[1], studentInfo[2],
					Integer.parseInt(studentInfo[3]),
					Integer.parseInt(studentInfo[4])));

		}

		// searching for a student...

		myExampleStudent = studentFactory.createStudent("Graduate", "Pravai",
				"Andra", 123456, Integer.parseInt("1200"));

		for (Student st : students) {
			if (st.equals(myExampleStudent)) {
				System.out.println("Da!!");
			}
		}

		// editing an existing student...
		for (Student st : students) {
			if (st.equals(myExampleStudent)) {
				st.setFirstName("notPravai");
				System.out.println("Modified student number "
						+ students.indexOf(st) + " : " + st);
				found = true;
			}
		}
		if (!found) {
			System.out.println("No such Student");
			found = false;
		}

		// remove a student...
		myExampleStudent.setFirstName("notPravai");
		for (Student st : students) {
			if (st.equals(myExampleStudent)) {
				System.out.println("Removed student number "
						+ students.indexOf(st) + " : " + st);
				students.remove(st);
				break;
			}
		}

		// adding a student
		students.add(1, myExampleStudent);
		System.out.println("Added 1 student on index " + 1 + " : "
				+ myExampleStudent);

		in.close();

	}

	// testing functionality with Database class
	public static void test2() throws NotAStudent, IncorrectName,
			NumberFormatException, FileNotFoundException, IOException {
		Database database = new Database();
		// searching
		System.out.println("-----------Searching Pravai Andra-------------\n");
		System.out.println(database.searchByFirstNameLastName("fsdf", "Andra"));
		// adding
		System.out.println("---------Adding gigica------------\n");
		database.addStudent("Graduate", "Gigica", "Petru", 111111,
				Integer.parseInt("1200"));
		System.out.println(database);
		System.out.println("---------Adding gigica again!------------\n");
		database.addStudent("Graduate", "Gigica", "Petru", 111111,
				Integer.parseInt("1200"));
		System.out.println(database);
		// remove
		System.out.println("----------Removing Gigica------------\n");
		database.remove("Gigica", "Petru");
		System.out.println(database);
		System.out.println("----------Removing Gigica again!------------\n");
		database.remove("Gigica", "Petru");
		System.out.println(database);
		// editing
		System.out.println("------------Editing Pravai Andra-----------");
		database.editInfo("Pravai", "Andra", "firstName", "NotPravai");
		System.out.println(database);
		System.out.println("---------Editing a non existing name--------");
		database.editInfo("Pravai", "Andra", "firstName", "NotPravai");
		System.out.println(database);
	}

	public static void main(String[] args) {
		try {
			test2();
		} catch (NotAStudent e) {
			System.out.println(e.getMessage());
			return;
		} catch (IncorrectName e) {
			System.out.println(e.getMessage());
			return;
		} catch (NumberFormatException e) {
			if (e.getMessage() != null) {
				String[] s = e.getMessage().split(" ");
				String output = s[s.length - 1];
				System.out.println("This is not a number " + output);
			} else
				System.out.println("It doesn't have 6 digits");
			return;
		} catch (FileNotFoundException e) {
			System.out.println("Verify that you have a file Database");

		} catch (IOException e) {
			System.out.println("IOException...");

		}
	}

}
