<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>allFailures</title>
</head>
<body>
<table><b>All devices:</b>
    <tr>

        <th>Type</th>
        <th>Airfield</th>
        <th>Created</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${allFailures}" var="failure">
        <tr>
            <td>${failure.device.type.name}</td>
            <td>${failure.device.airfield.abbr}</td>
            <td>${failure.created}</td>
            <td><a href="/failures/${failure.id}">Details</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
