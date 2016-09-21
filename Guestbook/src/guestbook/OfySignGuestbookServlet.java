// http://srisauce2.appspot.com/ofyguestbook.jsp
package guestbook;

 

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.google.appengine.api.users.User;

import com.google.appengine.api.users.UserService;

import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;

import java.io.IOException;


 

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

 

public class OfySignGuestbookServlet extends HttpServlet {

static {

        ObjectifyService.register(Greeting.class);

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)

                throws IOException {
    	Greeting greeting;

        UserService userService = UserServiceFactory.getUserService();

        User user = userService.getCurrentUser();

 
        // We have one entity group per Guestbook with all Greetings residing

        // in the same entity group as the Guestbook to which they belong.

        // This lets us run a transactional ancestor query to retrieve all

        // Greetings for a given Guestbook.  However, the write rate to each

        // Guestbook should be limited to ~1/second.

       String content = req.getParameter("content");
       String blog = req.getParameter("blog");
       
          greeting = new Greeting(user, content, blog);
        

        // Use Objectify to save the greeting and now() is used to make the call synchronously as we
        // will immediately get a new page using redirect and we want the data to be present.
       ofy().save().entity(greeting).now();
 

        resp.sendRedirect("/blogpost.jsp?guestbookName=" + greeting.getUser());

    }

}