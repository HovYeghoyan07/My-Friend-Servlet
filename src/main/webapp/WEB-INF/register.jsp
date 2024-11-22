<%--
  Created by IntelliJ IDEA.
  User: hovha
  Date: 19.11.2024
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>


    <h1>Register</h1>
    <%if (request.getAttribute("msg") != null) {%>
    <p style="color: red"><%=request.getAttribute("msg")%>
    </p>
    <%}%>
    <form action="/register" method="post" enctype="multipart/form-data">
        name: <input type="text" name="name"><br>
        surname: <input type="text" name="surname"><br>
        email: <input type="text" name="email"><br>
        password: <input type="password" name="password"><br>
        <input type="file" name="img"><br>
        <input type="submit" value="add">
    </form>

</body>
</html>
