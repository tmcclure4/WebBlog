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


public class Unsubscribe extends HttpServlet {
	static {

        ObjectifyService.register(EmailSingle.class);

    }

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
	//	ArrayList<String> emailList= new ArrayList<String>();
		 UserService userService = UserServiceFactory.getUserService();

	        User user = userService.getCurrentUser();
	        
    	  	List<EmailSingle> repeat = ofy().load().type(EmailSingle.class).list();
		  	EmailSingle del = new EmailSingle(user);

		  	for(EmailSingle sin : repeat)		//THIS IS WHERE I STOPPED WORKING
		  										//need to change the way entities are stored, so i can delete them
    	  	{
    	  		if(sin.getEmail().equals(user.getEmail())){
    	  		  
    	  			ofy().delete().type(EmailSingle.class).id(sin.getId()).now();
    	  			
    	  	       resp.sendRedirect("/blogpost.jsp");
    	  		}
    	  	
    	  	}
 
    	  
       resp.sendRedirect("/blogpost.jsp");

}
}
