package guestbook;

 

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.google.appengine.api.users.User;

import com.google.appengine.api.users.UserService;

import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;

import java.io.IOException;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

 

public class EmailServlet extends HttpServlet {

	static {

        ObjectifyService.register(EmailSingle.class);

    }

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
	//	ArrayList<String> emailList= new ArrayList<String>();
		EmailSingle emailList;
		 UserService userService = UserServiceFactory.getUserService();

	        User user = userService.getCurrentUser();
	        	
    	  emailList= new EmailSingle(user);
    	  	List<EmailSingle> repeat = ofy().load().type(EmailSingle.class).list();
    	  	for(EmailSingle sin : repeat)
    	  	{
    	  		if(sin.getEmail().equals(user.getEmail())){
    	  	       resp.sendRedirect("/blogpost.jsp");
    	  		}
    	  	
    	  	}
    		  ofy().save().entity(emailList).now();	
    		 // ofy().save().
    	  
       resp.sendRedirect("/blogpost.jsp");

}
}
