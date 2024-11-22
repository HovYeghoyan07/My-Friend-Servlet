<%@ page import="am.itspace.myfriendservlet.model.FriendRequest" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: hovha
  Date: 22.11.2024
  Time: 1:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Friend Responses</title>
</head>
<body>
<h1>Friend Responses</h1>
<% List<FriendRequest> friendRequests = (List<FriendRequest>) request.getAttribute("friendRequests");%>

<table border="1">
    <tr>
        <th>image</th>
        <th>name</th>
        <th>surname</th>
        <th>email</th>
        <th>action</th>
    </tr>
    <% for (FriendRequest friendRequest : friendRequests) { %>
    <tr>
        <td><img src="/getImg?imgName=<%=friendRequest.getFromId().getImageName()%>" width="80"></td>
        <td><%=friendRequest.getFromId().getName()%></td>
        <td><%=friendRequest.getFromId().getSurname()%></td>
        <td><%=friendRequest.getFromId().getEmail()%></td>
        <td><a href="/deleteFriendRequest?id=<%=friendRequest.getId()%>">delete</a> <a href="/accept?id=<%=friendRequest.getId()%>"></a></td>
    </tr>
    <% } %>
</table>
</body>
</html>
