<%--
  Created by IntelliJ IDEA.
  User: QT
  Date: 24/05/2022
  Time: 2:50 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>day la danh sach</h1>
<a href="/customers?huhi=create">tao moi</a>
<c:forEach var="cus" items="${dskh}">
    <h2>${cus.id}, ${cus.name} , ${cus.age}
        <a href="/customers?huhi=edit&id=${cus.id}">sua</a>,
        <a href="/customers?huhi=delete&id=${cus.id}">xoa</a>
    </h2>
</c:forEach>
</body>
</html>
