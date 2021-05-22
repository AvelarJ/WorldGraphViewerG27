package httpTest;
/**
 * This class is the real login server used for a login, which the proxy server makes a copy of, adding an additional layer of security.
 * @author Filip Maletic
 */
public class RealLoginServer implements LoginServer {

	/**
	 * Inherited login method.
	 */
	public boolean login(String username, String password) {

		return true;
	}
	
}