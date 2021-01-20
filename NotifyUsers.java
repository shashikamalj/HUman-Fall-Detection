package UserModule;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class NotifyUsers {
	private static final Logger logger = Logger.getLogger(NotifyUsers.class.getName());
	User user;
	ArrayList<String> emailNotSentList;
	boolean emailNotSentFlag;
	long thresholdTime=1200000;
	
	public NotifyUsers(User user) {
		this.user = user;
		this.emailNotSentList = new ArrayList<String>();
		this.emailNotSentFlag = false;
	}
	public void notifyByEmail() {
		ArrayList<String> dependents = getUser().getDependents();
		//long startTime = System.currentTimeMillis();
		while (emailNotSentFlag == false) {
			emailNotSentFlag = true;
			for(String dependent : dependents){
				if(!EmailNotification.sendMail(dependent,true,user)) {
					this.emailNotSentList.add(dependent);
					emailNotSentFlag = false;
				}
			/*long estimatedTime = System.currentTimeMillis() - startTime;
			if(estimatedTime >= thresholdTime) {
				System.out.println("Please check network access! Shutting down program");
				
				System.exit(1);
			}*/
			
		}
		dependents = new ArrayList<String>(emailNotSentList);
		this.emailNotSentList = new ArrayList<String>();
		
		}
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
