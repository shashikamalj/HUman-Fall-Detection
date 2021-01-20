package UserModule;

import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONOperations {
	@SuppressWarnings("finally")
	public static User readJSON(String filePath, String userID) {
		JSONParser parser=new JSONParser();
		User returnUser = new User();
		try {
			Object obj =  parser.parse(new FileReader(filePath));
			JSONObject jsonObject = (JSONObject) obj;
            JSONArray userList = (JSONArray) jsonObject.get("UserDetails");
            
            for (Object each_user : userList) {
            	JSONObject person = (JSONObject) each_user;

            	if(person.get("ID").toString().equals(userID)) {
            		returnUser.setId(person.get("ID").toString());
            		returnUser.setAge(person.get("Age").toString());
            		returnUser.setHeight(person.get("Height").toString());
            		returnUser.setName(person.get("Name").toString());
            		returnUser.setWeight(person.get("Weight").toString());
            		returnUser.setGender(person.get("Gender").toString());
            		
                    JSONArray dependentList = (JSONArray) person.get("Dependents");
                    ArrayList<String> dependents = new ArrayList<String>();
                    for(Object dependent : dependentList) {
                    //System.out.println(dependent.toString());
                    dependents.add(dependent.toString());
                    }
                    returnUser.setDependents(dependents);
            	}
            	
            }

		}
		
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			return returnUser;
		}
	}
	
	public static void main(String[] args) {
		String filePath = "C:\\Users\\Chinmay\\eclipse-workspace\\SCSProject_UserModule\\src\\UserModule\\UserData.json";
		String userID = "SA01";
		User user = new User();
		user =readJSON(filePath, userID);		
	}
}
