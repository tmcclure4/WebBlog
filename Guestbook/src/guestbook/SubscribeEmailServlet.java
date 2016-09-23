package guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;
import static com.googlecode.objectify.ObjectifyService.ofy;


import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import java.awt.Desktop;
import java.net.URI;

@SuppressWarnings("serial")
public class SubscribeEmailServlet extends HttpServlet{
	/*private static final Logger _logger = Logger.getLogger(SubscribeEmailServlet.class.getName());
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
	try {
	_logger.info("Cron Job has been executed");
	//Put your logic here
	//BEGIN
	//END
	}
	catch (Exception ex) {
	//Log any exceptions in your Cron Job
	}
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
	doGet(req, resp);
	}*/
	static {

        ObjectifyService.register(Greeting.class);

    }
	static {

        ObjectifyService.register(EmailSingle.class);

    }
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{
		List<EmailSingle> emails = ObjectifyService.ofy().load().type(EmailSingle.class).list(); 
		
		//////////////////////////////////////////////////////////////////////////////////////////	for email update
		List<Greeting> greetings = ObjectifyService.ofy().load().type(Greeting.class).list();
		List<Greeting> newArray = new ArrayList<Greeting>();
		for(Greeting sourceDay: greetings){
			if(newArray.isEmpty()){
				newArray.add(sourceDay);
			}
			else{
				for(int counter=0;counter<newArray.size();counter++){
	    			if(sourceDay.compareTo(newArray.get(counter)) == -1){
	    				newArray.add(counter, sourceDay);
	    				break;
	    			}
	    			else if(counter+1==newArray.size()){//its at the back of the array
	    				newArray.add(counter+=1,sourceDay);
	    			}
	    		}
			}
			
		}
		Collections.reverse(newArray);
		Date yesterday = new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L);
		List<Greeting> q = ofy().load().type(Greeting.class).filter("date >", yesterday).list();
		//////////////////////////////////////////////////////////////////////////////////////////////////////
		
		for(EmailSingle emai : emails){
		// Recipient's email ID needs to be mentioned.
	      String to = emai.getEmail();

	      // Sender's email ID needs to be mentioned
	      String from = "zygchess@gmail.com";//tmcclure@travisnewguestbook.appspotmail.com

	      // Assuming you are sending email from localhost
	      String host = "localhost";

	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      properties.setProperty("mail.smtp.host", host);

	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties);

	      try {
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject("461L BLOG!");

	         // Now set the actual message
	         
	        	// message.setText(g.getUser() + " posted about " + g.content);
	         message.setText("You are subscribed to the 461L blog.");

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
		}
		/*Properties props = new Properties();
	    Session session = Session.getDefaultInstance(props, null);

	    try {
	      Message msg = new MimeMessage(session);
	      msg.setFrom(new InternetAddress("tmcclure@travisnewguestbook.appspotmail.com", "Subscriber Email"));
	      msg.addRecipient(Message.RecipientType.TO,
	                       new InternetAddress("tmcclure@utexas.com", "Mr. User"));
	      msg.setSubject("Your Example.com account has been activated");
	      Transport.send(msg);
	    } catch (AddressException e) {
	      // ...
	    } catch (MessagingException e) {
	      // ...
	    } catch (UnsupportedEncodingException e) {
	      // ...
	    }
	    // [END simple_example]*/
	        //response.sendRedirect("/blogpost.jsp");

	  }
		 
	
	
	
}