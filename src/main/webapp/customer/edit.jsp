<%--
  Created by IntelliJ IDEA.
  User: QT
  Date: 25/05/2022
  Time: 10:59 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/customers?huhi=edit&id=${cansua.id}" method="post">
    <input type="number" name="id" value="${cansua.id}">
    <input type="text" name="name" value="${cansua.name}">
    <input type="number" name="age" value="${cansua.age}">
    <button>sua!</button>
</form>
</body>
</html>
