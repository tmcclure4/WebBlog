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
  
  
 <h2> Title</h2>
<form action="/ofysign" method="post">
 <div><textarea name="content" rows="1" cols="60"></textarea>
  <h2> Blog</h2>
   <textarea name ="blog" rows="10" cols="60"></textarea> </div>
    
 <div id = "content"><input type="submit" value="Post Greeting" /></div>
 <input type="hidden" name="guestbookName" value="${fn:escapeXml(guestbookName)}"/>
 </form> 

 
  <div id="container-foot">
    <div id="footer">
      <p><a href="#">homepage</a> | <a href="#">contact</a></p>
    </div>
  </div>
</div>

</body>
</html>
