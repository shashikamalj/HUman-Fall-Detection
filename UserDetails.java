package UserModule;

import java.util.ArrayList;
import java.util.List;

public class UserDetails {
	private ArrayList<String> dependents;
	private String username;
	
	public UserDetails(String username){
		
		this.username = username;
		
		@SuppressWarnings("serial")
		ArrayList<String> dependents =   new ArrayList<String>(){ 
	            { 
	                add("chinmay94@gmail.com"); 
	                add("iictechnovation@gmail.com");
	                //add("dsbvcwvs@hi2.in");
	            } 
	        };
	        setDependents(dependents);
	        
	}

	public List<String> getDependents() {
		return dependents;
	}
	public String getUsername() {
		return username;
	}
	public void setDependents(ArrayList<String> dependents) {
		this.dependents = new ArrayList<String>(dependents);
	}
	
	
}
