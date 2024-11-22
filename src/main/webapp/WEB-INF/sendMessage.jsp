<%@ page import="java.util.List" %>
<%@ page import="am.itspace.myfriendservlet.model.Message" %>
<%@ page import="am.itspace.myfriendservlet.model.User" %><%--
  Created by IntelliJ IDEA.
  User: hovha
  Date: 22.11.2024
  Time: 4:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Message</title>
</head>
<body>


<%List<Message> messages = ((List<Message>) request.getAttribute("messages"));%>
<h1>Message</h1>
<%for (Message message : messages) {%>
<%if (message.getFromId().getId() == ((User) request.getSession().getAttribute("user")).getId()) {%>
<p style="color: greenyellow"><%=message.getMessage()%></p>
<%} else {%>
<p style="color: #0023ff"><%=message.getMessage()%></p>
<%}%>
<%}%>
<form action="/sendMessage" method="post">
    <input type="hidden" name="friendId" value="idFriend">
    <textarea name="message"></textarea>
</form>
</body>
</html>
