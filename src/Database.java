import java.io.*;
import java.util.*;
public class Database {
	StudentFactory studentFactory;
	ArrayList<Student> students = new ArrayList<Student>();
	HashMap<Integer,Student> serieBuletinStudent = new HashMap<Integer,Student>();
	HashMap<String,Student> nameStudent =  new HashMap<String,Student>();
	public String[] breakLine(String line) {
		String[] studentInfo = new String[3];
		studentInfo = line.split(" ");
		return studentInfo;
	}
	public Database() throws FileNotFoundException, IncorrectName, NotAStudent, IOException{
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
	public Student searchByCardIdentity(Integer cardIdentity){
		return serieBuletinStudent.get(cardIdentity);
	}
	public Student searchByFirstNameLastName(String firstName, String lastName){
		String string = firstName.concat(lastName);
		return nameStudent.get(string);
	}
	public void add(Student s){
		students.add(s);
		serieBuletinStudent.put(s.serieBuletin, s);
		String string = s.firstName.concat(s.lastName);
		nameStudent.put(string,s);
	}

}
