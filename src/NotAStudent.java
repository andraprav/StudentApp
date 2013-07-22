/**
 * 
 * @author apravai
 * exception generated when a wrong member is typed ( not Graduate, Undergraduate or PartTime)
 */
public class NotAStudent extends Exception{
	public NotAStudent(String string) {
		super(string);
	}

}
