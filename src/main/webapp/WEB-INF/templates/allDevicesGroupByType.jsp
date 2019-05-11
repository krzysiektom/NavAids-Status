<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>allDevicesGroupByType</title>
</head>
<body>
<table><b>All devices group by type:</b>
    <tr>
        <th>Type</th>
        <th>Amount</th>
        <th>Ready</th>
        <th>Under-service</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${groupByTypes}" var="groupByType">
        <tr>
            <td>${groupByType.typeName}</td>
            <td>${groupByType.count}</td>
            <td>${groupByType.ready}</td>
            <td>${groupByType.underService}</td>
            <td><a href="/device/type/${groupByType.typeId}">Details</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
