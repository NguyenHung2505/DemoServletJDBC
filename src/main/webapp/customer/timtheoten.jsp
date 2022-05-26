<%--
  Created by IntelliJ IDEA.
  User: QT
  Date: 26/05/2022
  Time: 2:49 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post">
    <input type="text" name="name">
    <button>tim ten</button>
</form>
<c:forEach items="${cantim}" var="timdi">
    <h2>${timdi.id}, ${timdi.name}, ${timdi.age}
    </h2>
</c:forEach>
</body>
</html>
