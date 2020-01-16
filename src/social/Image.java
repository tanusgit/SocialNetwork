package social;

public class Image {
	String name;
	int id;
	String path;
	Image(String n, String p, int i ){
		name = n;
		path =p;
		id = i;
	}
	public String getName(){
		return name;
	}
	public int getId(){
		return id;
	}
	public String getPath(){
		return path;
	}
	public boolean setName(String m){
		if(m.isEmpty() || !Character.isAlphabetic(m.charAt(0))){
			return false;
		}
		name = m;
		return true;
	}
	public boolean SetId(int i){
		if(i<=0){
			return false;
		}
		id = i;
		return true;
	}
	public boolean setPath(String p){
		path = p;
		return true;
	}
}
