package social;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
/*
 * The User class represents a user in the social network.
 * All the user's information is in a file which has the same name
 * as username. Having a file with username as filename simplifies
 * things like having unique username in the network, searching for user
 * in the network etc. The user class is read from the file and written back
 * to the file when the program terminates.
 * 
 * The friends of a User are kept in an ArrayList to represent
 * an adjacency list.
 *
 * Format of the file:
 * username,password
 * Name
 * age
 * Status
 * list of friends
 */
public class User {
	String name;
	String userName;
	String password;
	int age = 1;
	public enum Status{Single, Married, Complicated, Unknown, InRelationship, Divorced}; 
	Status s = Status.Unknown;
	User friend;
	// Adjacency list for friends.
	ArrayList<String> friends = new ArrayList<String>();
	File userFile;
	User (File f) {
		userFile = f;
		try {
			Scanner br = new Scanner(new FileReader(f));
			String st = br.nextLine();
			String[] values = st.split(",");
			setUserName(values[0]);
			setPassword(values[1]);
			if (!br.hasNextLine()) {
				br.close();
				return;
			}
			st = br.nextLine();
			setName(st);
			if (!br.hasNextLine()) {
				br.close();
				return;
			}
			int age = br.nextInt();
			setAge(age);
			if (!br.hasNextLine()) {
				br.close();
				return;
			}
			br.nextLine(); // Skip newline of Int
			st = br.nextLine();
			setStatus(st);
			while (br.hasNextLine()) {
				st = br.nextLine();
				addFriend(st);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//Constructor of user class
	User(String n, int a, Status k){
		name = n;
		age = a;
		s = k;
	}
	//adding friend to friend list
	void addFriend(String u){
		for(String s : friends) {
			// If the friend already exists then don't add.
			if (s.equals(u))
				return;
		}
		friends.add(u);
	}
	//removing friend from friend list
	void removeFriend(String u){
		friends.remove(u);
	}
	// Return list of friends.
	ArrayList<String> getFriends(){
		return friends;
	}
	public String getName(){
		return name;
	}
	public int getAge(){
		return age;
	}
	public Status getStatus(){
		return s;
	}
	public boolean setUserName(String m){
		userName = m;
		// Name is userName by default.
		name = m;
		return true;
	}
	public String getUserName(){
		return userName;
	}
	public boolean setPassword(String m){
		password = m;
		return true;
	}
	public String getPassword(){
		return password;
	}
	public boolean setName(String m){
		name = m;
		return true;
	}
	public boolean setAge(int a){
		if (a < 0){
			return false;
		}
		age = a;
		return true;
	}
	public boolean setStatus(String s) {
		// 	enum Status{Single, Married, Complicated, Unknown, InRelationship, Divorced}; 
		switch(s){
		case "Single":
			setStatus(Status.Single); break;
		case "Married":
			setStatus(Status.Married); break;
		case "Complicated":
			setStatus(Status.Complicated); break;
		case "Unknown":
			setStatus(Status.Unknown); break;
		case "InRelationship":
			setStatus(Status.InRelationship); break;
		case "Divorced":
			setStatus(Status.Divorced); break;
		default:
			setStatus(Status.Unknown); break;
		}
		return true;
	}
	public boolean setStatus(Status k){
		s = k;
		return true;
	}
	// Write the Users account information to the file.
	// This is used to update the user information.
	public boolean writeFile() {
		try{
			BufferedWriter br = new BufferedWriter(new FileWriter(userFile));
			String st = getUserName() + "," + getPassword() + "\n";
			st += getName() + "\n";
			st += getAge() + "\n";
			st += getStatus() + "\n";
			for(String f : getFriends()){
	            st += f;
	            st += "\n";
	        }
			br.write(st);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	// To help with printing the user information on console.
	public String toString() {
		String st = "Username:" + getUserName() + "\n";
		st += "Name:" + getName() + "\n";
		st += "Age:" + getAge() + "\n";
		st += "Status:" + getStatus() + "\n";
		st += "FriendsList:\n";
		for(String f : getFriends()){
            st += f;
            st += "\n";
        }
		return st;
	}
}
