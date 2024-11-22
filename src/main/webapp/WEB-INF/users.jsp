<%@ page import="am.itspace.myfriendservlet.model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: hovha
  Date: 21.11.2024
  Time: 23:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h1>Users</h1>
<% List<User> users = (List<User>) request.getAttribute("users");%>


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
    <td><a href="/sendRequest?userId=<%=user.getId()%>">Send Request</a><%=user.getEmail()%></td>

  </tr>
  <% } %>
</table>
</body>
</html>
