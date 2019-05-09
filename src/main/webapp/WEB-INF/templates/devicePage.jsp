<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>devicePage</title>
</head>
<body>
<table><b>Device:</b>
    <tr>
        <th>Group</th>
        <th>Type</th>
        <th>Status</th>
        <th>Factory Number</th>
        <th>Owner</th>
        <th>Airfield</th>
    </tr>
    <tr>
        <td>${device.type.group.abbr}</td>
        <td>${device.type.name}</td>
        <td>${device.ready}</td>
        <td>${device.factoryNumber}</td>
        <td>${device.owner.abbr}</td>
        <td>${device.airfield.abbr}</td>
    </tr>
</table>
</body>
</html>
