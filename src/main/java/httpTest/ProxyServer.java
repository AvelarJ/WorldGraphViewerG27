package httpTest;
/**
 * This class is the Proxy server which creates a substitute Server that keeps a reference to the RealLogin Server.
 * @author Filip Maletic
 */
public class ProxyServer implements LoginServer {
	
	/**
	 * Reference to the "real" object
	 */
	private RealLoginServer realLoginServer;
	
	/**
	 * User object
	 */
	private User user;
	
	/**
	 * This constructor takes a User object as a parameter to create a ProxyServer object.
	 * @param user User object
	 */
	public ProxyServer(User user) {
		this.user = user;
	}
	/**
	 * This inherited method attempts a login using a RealLoginServer object.
	 * @param username		The username that the user enters in the login screen
	 * @param password		The password that the user enters in the login screen
	 * @return				returns a boolean value
	 */
	public boolean login(String username, String password) {
		
		// Set flag
		
		Boolean valid = false;
		
		// If no existing "real" object exists, then instantiate new object
		
		if (realLoginServer == null) {
			realLoginServer = new RealLoginServer();
		}
		
		// Verify login credentials
		
		if (user.validate(username, password)) {		
			realLoginServer.login(username, password);
			valid = true;
		}

		return valid;
		
	}
	
}