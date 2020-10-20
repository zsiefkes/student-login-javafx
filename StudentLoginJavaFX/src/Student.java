import java.util.ArrayList;
import java.util.TreeMap;

public class Student {
	private String name;
	private String email;
	private String password;
	private int uniqueId;
	
	private static ArrayList<Student> students = new ArrayList<>();
	private static TreeMap<String, Integer> emailsIds = new TreeMap<>();
	
	public Student(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
		
		// set id based on number of existing students
		this.uniqueId = emailsIds.size() + 1;
		emailsIds.put(email, uniqueId);
		
		// add student to arraylist
		students.add(this);
	}

	// check if email exists in system
	public static boolean emailExists(String email) {
		return emailsIds.containsKey(email);
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}

	public static ArrayList<Student> getStudents() {
		return students;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", email=" + email + ", password=" + password + ", uniqueId=" + uniqueId + "]";
	}
	
	
}
