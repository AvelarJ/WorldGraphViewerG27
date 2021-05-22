package httpTest;
/**
 * This interface acts as the login server and contains one login method that the ProxyServer and RealLogin Server will implement.
 * @author Filip Maletic
 */
public interface LoginServer {
	/**
	 * This is the login method that the children classes will implement.
	 * @param username		The username that the user enters in the login screen
	 * @param password		The password that the user enters in the login screen
	 * @return
	 */
	public boolean login(String username, String password);
	
}