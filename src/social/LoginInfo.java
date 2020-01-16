package social;

// LoginInfo represents the information entered when a user tries
// to login to the network. It is simple class with only username
// and password.
public class LoginInfo {
	private String userName;
	private String password;

	public LoginInfo(String name, String pass) {
		userName = name;
		password = pass;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}