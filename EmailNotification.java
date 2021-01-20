package UserModule;

import java.util.*; 
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import java.lang.Object;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EmailNotification {

   public static boolean sendMail(String to, boolean dependentIsADoctor,User user) {
	   boolean returnNoError = false;

      // Sender's email ID needs to be mentioned
      String from = "collegeprojects195@gmail.com";

      // Assuming you are sending email from localhost
      String host = "localhost";

      // Get system properties
      Properties properties = System.getProperties();
      final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
      
      // Setup mail server
      properties.setProperty("mail.smtp.host", host);
      properties.setProperty("mail.user", to);
      properties.setProperty("mail.password", "chInmAy123");
      properties.setProperty("mail.smtp.host", "smtp.gmail.com");
      properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
      properties.setProperty("mail.smtp.socketFactory.fallback", "false");
      properties.setProperty("mail.smtp.port", "465");
      properties.setProperty("mail.smtp.socketFactory.port", "465");
      properties.put("mail.smtp.auth", "true");
      properties.put("mail.debug", "true");
      properties.put("mail.store.protocol", "pop3");
      properties.put("mail.transport.protocol", "smtp");
      properties.put("mail.smtp.sendpartial", "true");
      String password="Infosys1234";
      returnNoError = InternetAvailabilityChecker.checkInternet(host);
      
      if (returnNoError ) {
      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties, 
              new Authenticator(){
          protected PasswordAuthentication getPasswordAuthentication() {
             return new PasswordAuthentication(from, password);
          }});

      try {
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("A Fall Occured to "+user.getName());

         DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	 Calendar cal = Calendar.getInstance();
    	 System.out.println(sdf.format(cal.getTime()));
    	 
         // Now set the actual message
         //message.setText(");
         
         // Create the message part
         BodyPart messageBodyPart = new MimeBodyPart();

         // Now set the actual message
         messageBodyPart.setText("A Fall Occured to "+user.getName()+" at "+sdf.format(cal.getTime()));

         // Create a multipar message
         Multipart multipart = new MimeMultipart();

         // Set text message part
         multipart.addBodyPart(messageBodyPart);
         if(dependentIsADoctor) {
         // Part two is attachment
        	 messageBodyPart = new MimeBodyPart();
        	 String filename = "C:\\Users\\Chinmay\\eclipse-workspace\\SCSProject_UserModule\\src\\UserModule\\"+user.getId()+".txt";
        	 DataSource source = new FileDataSource(filename);
        	 messageBodyPart.setDataHandler(new DataHandler(source));
        	 messageBodyPart.setFileName("Fall Data for "+user.getId()+".txt");
        	 multipart.addBodyPart(messageBodyPart);
         }
         
         // Send the complete message parts
         message.setContent(multipart);

         // Send message
         Transport.send(message);
         
         System.out.println("Sent message successfully....");
         returnNoError = true;
      } 
      //catch (SMTP)
      catch (MessagingException mex) {
    	  returnNoError = false;
    	  mex.printStackTrace();
    	  return returnNoError;
      }
      }
      else {
    	  returnNoError = false;
    	  System.out.println("Internet is down"); 
      }
      return returnNoError;
     
   }
   
}

