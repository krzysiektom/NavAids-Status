<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>allDevicesGroupByTypeOrderByGroup</title>
</head>
<body>
<table><b>All devices group by type order by group:</b>
    <tr>
        <th>Type</th>
        <th>Amount</th>
        <th>Ready</th>
        <th>Under-service</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${groups}" var="group">
        <tr>
            <td colspan="5">${group.name}</td>
        </tr>
        <c:forEach items="${deviceService.findAllTypesByGroup(group)}" var="type">
            <tr>
                <td>${type.name}</td>
                <td>${deviceService.countByType(type)}</td>
                <td>${deviceService.countByTypeAndReadyTrue(type)}</td>
                <td>${deviceService.countByTypeAndReadyFalse(type)}</td>
                <td><a href="/device/type/${type.id}">Details</a></td>
            </tr>
        </c:forEach>
    </c:forEach>
</table>

</body>
</html>
