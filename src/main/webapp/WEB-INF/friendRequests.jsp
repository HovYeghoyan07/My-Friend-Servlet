<%@ page import="am.itspace.myfriendservlet.model.FriendRequest" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: hovha
  Date: 22.11.2024
  Time: 3:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Friend Requests</title>
</head>
<body>
<h1>Friend Requests</h1>
<% List<FriendRequest> friendRequests = (List<FriendRequest>) request.getAttribute("friendRequests");%>

<table border="1">
  <tr>
    <th>image</th>
    <th>name</th>
    <th>surname</th>
    <th>email</th>
  </tr>
  <% for (FriendRequest friendRequest : friendRequests) { %>
  <tr>
    <td><img src="/getImg?imgName=<%=friendRequest.getToId().getImageName()%>" width="80"></td>
    <td><%=friendRequest.getToId().getName()%></td>
    <td><%=friendRequest.getToId().getSurname()%></td>
    <td><%=friendRequest.getToId().getEmail()%></td>
  </tr>
  <% } %>
</table>
</body>
</html>
