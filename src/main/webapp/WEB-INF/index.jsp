<%@ page import="am.itspace.myfriendservlet.model.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Friend</title>
</head>
<body>
<h1>My Friend
</h1>

<% User user = (User) session.getAttribute("user"); %>
<%if (user != null) { %>
<span>Welcome <%=user.getName()%></span> <a href="/logout">logout</a>
<%} else { %>
<a href="/login">Login</a> <br>


<%}%>

<br/>
<a href="/users">View All Users</a>
<a href="/friendResponses">View Friend Responses </a><br>
<a href="/friendRequests">View Friend Requests </a>
<a href="/friends">View Friends </a>

</body>
</html>
