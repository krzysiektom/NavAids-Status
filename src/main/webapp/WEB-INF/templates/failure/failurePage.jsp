<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>failurePage</title>
</head>
<body>
<table><b>Failure details:</b>
    <tr>

        <th>Type</th>
        <th>Airfield</th>
        <th>Description</th>
        <th>Created</th>
    </tr>
    <tr>
        <td>${failure.device.type.name}</td>
        <td>${failure.device.airfield.abbr}</td>
        <td>${failure.description}</td>
        <td>${failure.created}</td>
    </tr>
</table>
<table><b>Fixes details:</b>
    <tr>

        <th>Done</th>
        <th>Used material</th>
        <th>Created</th>
    </tr>
    <c:forEach items="${allFixes}" var="fix">
        <tr>
            <td>${fix.done}</td>
            <td>${fix.usedMaterials}</td>
            <td>${fix.created}</td>
            <td><a href="/fix/${fix.id}">Details</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
