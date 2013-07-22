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

	public static void main(String[] args) {
		StudentFactory studentFactory = StudentFactory.getInstance();
		BufferedReader in = null;
		String line;
		String[] studentInfo;
		ArrayList<Student> students = new ArrayList<Student>();
		try {
			in = new BufferedReader(new FileReader("Database"));
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFound!");
		}
		try {
			while ((line = in.readLine()) != null) {
				studentInfo = breakLine(line);
				students.add(studentFactory.createStudent(studentInfo[0],
						studentInfo[1], studentInfo[2],
						Integer.parseInt(studentInfo[3]),
						Integer.parseInt(studentInfo[4])));

			}
		} catch (IOException e) {
			System.out.println("IOException");
		}
		for(int studentCounter = 0 ; studentCounter<10; studentCounter+=2){
			System.out.println(":" +
			students.get(studentCounter).getHoursPerWeek());
		}
	}

}
