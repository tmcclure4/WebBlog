<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>

<%@ page import="com.google.appengine.api.users.User" %>

<%@ page import="com.google.appengine.api.users.UserService" %>

<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

<%@ page import="com.googlecode.objectify.*" %>

<%@ page import="guestbook.Greeting" %>

<%@ page import="java.util.Collections" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<head>
<title>461Lblog</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
   <link type="text/css" rel="stylesheet" href="/stylesheets/main.css" />
</head>
<body>

<div id="container">
  <div id="banner">
    <h1>461L Blogspot</h1>
  </div>
  <div id="navcontainer">
    <ul id="navlist">
      <li id="active"><a id="current" href="#">HOMEPAGE</a></li>
    </ul>
  </div>
  <div id="sidebar">
    <h2>Posts</h2>
    <div class="navlist">
      <ul>
        <li><a href="#">Older Posts</a></li>
        <li><a href="#">Random</a></li>
      </ul>
    </div>
 
  
  </div>
  <div id="content">
  <%
    String guestbookName = request.getParameter("guestbookName");

    if (guestbookName == null) {

        guestbookName = "default";

    }

    pageContext.setAttribute("guestbookName", guestbookName);

    UserService userService = UserServiceFactory.getUserService();

    User user = userService.getCurrentUser();

    if (user != null) {

      pageContext.setAttribute("user", user);

%>
    <h2>Hello, ${fn:escapeXml(user.nickname)}!</h2>
    <h2>( You can <a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">sign out</a>.)</h2>
    <%

    } else {

%>
<p>Hello!

<a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in</a>

to include your name with greetings you post.</p>

<%

    }
%>
   
    <p class="info"><img class="noborder" src="/stylesheets/img/flower.jpg" alt="flower" title="flower"/> </p>
<%


	ObjectifyService.register(Greeting.class);

	List<Greeting> greetings = ObjectifyService.ofy().load().type(Greeting.class).list(); 

	Collections.reverse(greetings);
			
		

	if (greetings.isEmpty()) {

        %>

        <p>Guestbook '${fn:escapeXml(guestbookName)}' has no messages.</p>

        <%

    } else {

        %>

        <p>Messages in Guestbook '${fn:escapeXml(guestbookName)}'.</p>

        <%
        for (Greeting greeting : greetings) {
			int count = 0;
	       	if(count == 3) break;
            pageContext.setAttribute("greeting_content",

                                     greeting.getContent());
            pageContext.setAttribute("greeting_blog", 
            						 greeting.getBlog());
	
            if (greeting.getUser() == null) {

                %>

                <p>An anonymous person wrote:</p>

                <%

            } else {

                pageContext.setAttribute("greeting_user",

                                         greeting.getUser());
				pageContext.setAttribute("date", greeting.getDate());
                %>
                <p><b>${fn:escapeXml(greeting_user.nickname)}</b> wrote:</p>

                <%

            }

            %>
			
            <h2>${fn:escapeXml(greeting_content)}</h2>
            <blockquote>${fn:escapeXml(greeting_blog)}</blockquote>
            				<b>${fn:escapeXml(date)}</b>
            
			
            <%
			count++;
        }

    }


 if(user != null){ %>
      <div><input type="button" value="Post New Blog" onClick="openPage('posting.jsp')"/></div>
      <% } %>
      
<script type="text/javascript">
 function openPage(pageURL)
 {
 window.location.href = pageURL;
 }
</script>

    
    <p class="post">  <a href="#" class="comments">Comments</a> </p>
          <form action="/cron/subscribeemailjob" >
      <input type="submit" value="Subscribe"/>
 </form> 
  </div>
  <div id="container-foot">
    <div id="footer">
      <p><a href="#">homepage</a> | <a href="#">contact</a></p>
		
    </div>
  </div>
</div>

</body>
</html>
