import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Student {
	private String name;
	private String email;
	private String password;
	private int uniqueId;
	
	private static ArrayList<Student> students = new ArrayList<>();
	private static TreeMap<String, Integer> emailsIds = new TreeMap<>();
	private static HashMap<String, String> emailsPasswords = new HashMap<>();
	
	public Student(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password; // no encryption 'cause we haven't learned that yet...
		
		// set id based on number of existing students
		this.uniqueId = emailsIds.size() + 1;
		emailsIds.put(email, uniqueId);
		emailsPasswords.put(email, password);
		
		// add student to arraylist
		students.add(this);
	}

	// check if email exists in system
	public static boolean emailExists(String email) {
		return emailsIds.containsKey(email);
	}
	
	// check password matches email. returns false if email not found.
	public static boolean checkPasswordMatches(String email, String password) {
		if (emailsPasswords.containsKey(email)) {
			if (emailsPasswords.get(email).equals(password)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	public static Student getStudent(int id) {
		for (Student s : students) {
			if (s.getUniqueId() == id) {
				return s;
			}
		}
		// if no student found
		return null;
	}
	
	public static int getStudentIDFromEmail (String email) {
		if (emailsIds.containsKey(email)) {
			return emailsIds.get(email);
		} else {
			// if no student found
			return 0;
		}
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
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}

	public int getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}

	public static ArrayList<Student> getStudents() {
		return students;
	}
	
	public static TreeMap<String, Integer> getEmailsIds() {
		return emailsIds;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", email=" + email + ", password=" + password + ", uniqueId=" + uniqueId + "]";
	}
	
	
}
