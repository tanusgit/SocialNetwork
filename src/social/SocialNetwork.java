package social;

import java.io.File;
import java.io.IOException;
import java.util.*;

// The top level file that defines the social network.
// The network allows the user to join the network,
// see and edit profile.
// logout, add friends, search for friends based on username
// see a user's complete profile including list of their friends.

public class SocialNetwork {
	ArrayList<User> Users = new ArrayList<User>();
	Scanner s = new Scanner(System.in);

	// The main function just calls the SocialNetwork constructor
	// which drives the interactions with the user.
	public static void main(String[] args) {
		new SocialNetwork();
	}

	// Authenticate a user by comparing the password entered with that
	// in the file.
	public boolean authenticate(LoginInfo LI) {
		if (!AccountInfo.authenticateUser(LI)) {
			// Print if the user wants to create an account.
			System.out.println("User does not exist. Do you want to create an accout [Y/n]?");
			char ch;
			//ch = (char) System.in.read();
			String un = s.nextLine();
			ch = un.charAt(0);

			if (ch == 'Y' || ch == 'y') {
				AccountInfo.addUser(LI);
				System.out.println("User added, login successful...");
				return true;
			}

			return false;
		}
		return true;
	}
	// Searches the entire directory to get all the accounts and store them
	// in an ArrayList. This helps with search etc.
	public void populateAllUsers() {
		// Read all the users from the directory;
		String path = "C:\\Users\\TD\\Desktop\\foothill-esl-249\\SJSU\\Data-Structure146\\social-network-project\\users";
		File[] faFiles = new File(path).listFiles();
		for (File file : faFiles) {
			User u = new User(file);
			Users.add(u);
		}
	}
	// Searches for a user by username.
	public User searchUser(String s) {
		if (Users.isEmpty())
			populateAllUsers();
		for (User u : Users) {
			if (u.getUserName().equals(s)) {
				return u;
			}
		}
		return null;
	}

	// Add a friend to user 'u' by asking the user to enter a username.
	// If the username exist in the network they will be added to the
	// friends list.
	public void addFriend(User u) {
		System.out.println("Enter username name to add:");
		String un = s.nextLine();
		User f = searchUser(un);
		if (f != null) {
			u.addFriend(f.getUserName());
			f.addFriend(u.getUserName());
			System.out.println("Congratulations! " + f.getUserName()
					+ " is your friend now");
		} else {
			System.out.println("user does not exist.");
		}
	}
	// Edit the profile of user 'u' by interactively prompting the user
	// Note that username can't be changed.
	public void editProfile(User u) {
		System.out.println("What would you like to modify?");
		System.out.println("Enter name, press enter to keep existing:");
		String p = s.nextLine();
		if (!p.isEmpty())
			u.setName(p);
		System.out.println("Enter age, press enter to keep existing:");
		p = s.nextLine();
		if (!p.isEmpty()) {
			try {
				int a = Integer.parseInt(p);
				u.setAge(a);
			} catch (NumberFormatException e) {
				System.out.println("Invalid age, age must be a number and "
						+ "greater than zero, skipping...");
			}
		}
		System.out.println("Enter status, valid status are: Single,"
				+ " Married, Complicated, Unknown,"
				+ " InRelationship, Divorced");
		p = s.nextLine();
		if (!p.isEmpty()) {
			try {
				u.setStatus(User.Status.valueOf(p));
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid status, valid status are: Single,"
						+ " Married, Complicated, Unknown,"
						+ " InRelationship, Divorced");
			}
		}
	}
	// Prints the profile of user 'u'
	public void showProfile(User u) {
		System.out.println(u);
	}
	// Interactively search for a person on the network. Prompts the user
	// to enter a username and prints their profile if such username exists.
	public void searchUser() {
		// Prompt for a user name
		System.out.println("Enter the username to search:");
		String p = s.nextLine();
		User u = searchUser(p);
		if (u != null) {
			System.out.println(u);
		} else {
			System.out.println("User not found");
		}
	}
	// See the friends list of the user 'u'
	public void showFriendsList(User u) {
		System.out.println(u.getFriends());
	}
	// Interacts with the user by prompting the user
	// to enter one of the options.
	public void StartInteraction(User u) {
		while (true) {
			System.out.println("Welcome " + u.getName());
			System.out.println("What would you like to do?");
			System.out.println("Enter 'a' to add a friend\n");
			System.out.println("Enter 'e' to edit profile\n");
			System.out.println("Enter 'l' to show friend list\n");
			System.out.println("Enter 'o' to logout\n");
			System.out.println("Enter 'p' to show profile\n");
			System.out.println("Enter 's' to search a user\n");
			String p = s.nextLine();
			switch (p) {
			case "a":
				addFriend(u);
				break;
			case "e":
				editProfile(u);
				break;
			case "l":
				showFriendsList(u);
				break;
			case "o":
				return;
			case "p":
				showProfile(u);
				break;
			case "s":
				searchUser();
				break;
			default:
				break;
			}
		}
	}
	//constructor of SocialNetwork. This prompts the user to enter user
	//name and password,
	//if the authentication is successful it continues the interaction, 
	//otherwise it gives user to create a new account
	public SocialNetwork() {
		System.out.println("Welcome to social network!\n");
		System.out.println("Username:");
		String u = s.nextLine();
		System.out.println("Password:");
		String p = s.nextLine();

		LoginInfo LI = new LoginInfo(u, p);
		if (authenticate(LI)) {
			System.out.println("Authentication successful for: " + LI.getUserName());
			User un = searchUser(LI.getUserName());
			if (un != null) {
				StartInteraction(un);
				un.writeFile();
			}
			else {
				System.out.println("User could not be found!\n");				
			}
		} else {
			System.out.println("Authentication unsuccessful, exiting...\n");
			return;
		}
		System.out.println("Goodbye!");
	}
}

