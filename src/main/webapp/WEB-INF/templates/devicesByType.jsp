<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>devicesByType</title>
</head>
<body>
<table><b>All devices by type: "${type.name}"</b>
    <tr>
        <th>Airfield</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${allDevices}" var="device">
        <tr>
            <td>${device.airfield.abbr}</td>
            <td>${device.ready}</td>
            <td><a href="/device/${device.id}">Details</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
