package httpTest;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * This class represents a user attempting to login to the system. This class has two instance variables, a username and a password.
 * @author Filip Maletic
 */
public class User {
	
	/**
	 * Username instance variable
	 */
	private String username;
	/**
	 * Password instance variable
	 */
	private String password;
	
	/**
	 * Constructor that creates a User object using the username and password instance variables
	 * @param username		The username that the user enters in the login screen.
	 * @param password		The password that the user enters in the login screen.
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	/**
	 * This method gets and returns a string containing the username of a User object.
	 * @return
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * This method gets and returns a string containing the password of a User object.
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * This method validates if the username and password combination entered by the user is found in the login server (text file) and matches up.
	 * @param username		The username that the user enters in the login screen
	 * @param password		The password that the user enters in the login screen
	 * @return
	 */
	public Boolean validate(String username, String password) {
		ArrayList<String> usernames = new ArrayList<String>();		// Arraylist will store the usernames found in the text file
		ArrayList<String> passwords = new ArrayList<String>();		// Arraylist will store the passwords found in the text file
		
		try {
			BufferedReader in = new BufferedReader(new FileReader("LoginCredentials.txt"));	
			in.readLine();		// Skips header/explanatory line
			String line;
			while ((line = in.readLine()) != null) {
		          String[] credentials = line.split(" ");		// Splits each line at the blank space which separates the username and password of a user and stores the two in a string array.
		          usernames.add(credentials[0]);			// Adds the username stored in the pair array to the usernames ArrayList
		          passwords.add(credentials[1]);			// Adds the password stored in the pair array to the passwords ArrayList
		      }
			in.close();
		}
		catch (IOException e) {
			System.out.println("Unable to read file");		// Displays error message if file cannot be found.
			System.exit(0);
		}
		
		for (int i = 0; i < usernames.size(); i++) {
			// Checks if the entered username and corresponding password are stored in a single line in the LoginCredentials text file.
			if (usernames.get(i).contains(username) && passwords.get(i).contains(password)) {
				return true;
			}
		}
		return false;		// If the entered username and/or password cannot be found in the text file, the function returns false.
	}
	
}