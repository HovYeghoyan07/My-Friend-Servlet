<%@ page import="am.itspace.myfriendservlet.model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: hovha
  Date: 22.11.2024
  Time: 3:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Friends</title>
</head>
<body>
<h1>Friends</h1>
<% List<User> users = (List<User>) request.getAttribute("friends");%>


<table border="1">">
    <tr>
        <th>image</th>
        <th>name</th>
        <th>surname</th>
        <th>email</th>
        <th>action</th>
    </tr>
    <% for (User user : users) { %>
    <tr>
        <td><img src="/getImg?imgName=<%=user.getImageName()%>" width="80"></td>
        <td><%=user.getName()%></td>
        <td><%=user.getSurname()%></td>
        <td><%=user.getEmail()%></td>
        <td><a href="/sendMessage?id=<%=user.getId()%>">Send Message</a></td>

    </tr>
    <% } %>
</table>
</body>
</html>
