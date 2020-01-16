package social;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
// Simple class to represent an account. This is used to:
// 1. authenticate a user
// 2. create new account
// 3. get user data after successful login.
public class AccountInfo {
	// Authenticate a user with their username and password.
	// The username and password are stored in users file.
	public static boolean authenticateUser(LoginInfo login) {
		String path = "C:\\Users\\TD\\Desktop\\foothill-esl-249\\SJSU\\Data-Structure146\\social-network-project\\users";
		String userFile = path + "\\" + login.getUserName();
		// Open the file with username as the filename.
		File f = new File(userFile);
		boolean exists = f.exists();
		if (!exists)
			return false;
		try {
			// First line contains the username and the password
			BufferedReader br = new BufferedReader(new FileReader(f));
			String st = br.readLine();
			br.close();
			String[] values = st.split(",");
			//System.out.println("User exists with:" + values[0] + "," + values[1] + ", LI:" + login.getUserName() + "," + login.getPassword());
			if (values[0].equals(login.getUserName()) && values[1].equals(login.getPassword()))
				return true;
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	}
	// Add a user to the network. Create a file with login.getUserName()
	public static boolean addUser(LoginInfo login) {
		String path = "C:\\Users\\TD\\Desktop\\foothill-esl-249\\SJSU\\Data-Structure146\\social-network-project\\users";
		String userFile = path + "\\" + login.getUserName();
		// Validate username: if the username has non-alpha characters ask to re-enter.
		
		// Open the file with username as the filename.
		File f = new File(userFile);
		try {
			// First line contains the username and the password
			BufferedWriter br = new BufferedWriter(new FileWriter(f));
			String st = login.getUserName() + "," + login.getPassword() + "\n";
			br.write(st);
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	// Each user has a unique file with the same name as username.
	// If a file with login.getUserName() exists that means the user exist.
	public User getUser(LoginInfo login) {
		String path = "C:\\Users\\TD\\Desktop\\foothill-esl-249\\SJSU\\Data-Structure146\\social-network-project\\users";
		String userFile = path + "\\" + login.getUserName();
		// Open the file with username as the filename.
		File f = new File(userFile);
		boolean exists = f.exists();
		assert(exists);
		User u = new User(f);
		return u;
	}
}