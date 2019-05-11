<%--
  Created by IntelliJ IDEA.
  User: krzysztof
  Date: 10.05.19
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>allDevicesGroupByOwner</title>
</head>
<body>
<table><b>All devices group by owner:</b>
    <tr>
        <th>Type</th>
        <th>Ready</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${groupByOwners}" var="groupByOwner">
        <tr>
            <td colspan="3">${groupByOwner.owner.abbr}</td>
        </tr>
        <c:forEach items="${groupByOwner.devices}" var="device">
            <tr>
                <td>${device.type.name}</td>
                <td>${device.ready}</td>
                <td><a href="/device/${device.id}">Details</a></td>
            </tr>
        </c:forEach>
    </c:forEach>
</table>
</body>
</html>
