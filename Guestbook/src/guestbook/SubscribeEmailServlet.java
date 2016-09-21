package guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{
    	Greeting greeting;

        UserService userService = UserServiceFactory.getUserService();

        User user = userService.getCurrentUser();
		// Recipient's email ID needs to be mentioned.
	      String to = user.getNickname();

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
	         message.setSubject("This is the Subject Line!");

	         // Now set the actual message
	         message.setText("This is actual message");

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
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
	  }
		 
	
	
	
}




/*	public void doGet(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException
{
System.out.println("TESTING PLEASE WORK");
// Recipient's email ID needs to be mentioned.
String to = "tmcclure4@yahoo.com";

// Sender's email ID needs to be mentioned
String from = "web@gmail.com";

// Assuming you are sending email from localhost
String host = "localhost";

// Get system properties
Properties properties = System.getProperties();

// Setup mail server
properties.setProperty("mail.smtp.host", host);

// Get the default Session object.
Session session = Session.getDefaultInstance(properties);

// Set response content type
response.setContentType("text/html");
PrintWriter out = response.getWriter();

try{
 // Create a default MimeMessage object.
 MimeMessage message = new MimeMessage(session);
 // Set From: header field of the header.
 message.setFrom(new InternetAddress(from));
 // Set To: header field of the header.
 message.addRecipient(Message.RecipientType.TO,
                          new InternetAddress(to));
 // Set Subject: header field
 message.setSubject("This is the Subject Line!");
 // Now set the actual message
 message.setText("This is actual message");
 // Send message
 Transport.send(message);
 String title = "Send Email";
 String res = "Sent message successfully....";
 String docType =
 "<!doctype html public \"-//w3c//dtd html 4.0 " +
 "transitional//en\">\n";
 out.println(docType +
 "<html>\n" +
 "<head><title>" + title + "</title></head>\n" +
 "<body bgcolor=\"#f0f0f0\">\n" +
 "<h1 align=\"center\">" + title + "</h1>\n" +
 "<p align=\"center\">" + res + "</p>\n" +
 "</body></html>");
}catch (MessagingException mex) {
mex.printStackTrace();
}
}

*/
