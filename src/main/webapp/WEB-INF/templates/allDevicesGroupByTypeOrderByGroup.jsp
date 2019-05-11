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
    <c:forEach items="${groupByGroups}" var="groupByGroup">
        <tr>
            <td colspan="5">${groupByGroup.group.name}</td>
        </tr>
        <c:forEach items="${groupByGroup.groupByTypes}" var="groupByType">
            <tr>
                <td>${groupByType.typeName}</td>
                <td>${groupByType.count}</td>
                <td>${groupByType.ready}</td>
                <td>${groupByType.underService}</td>
                <td><a href="/device/type/${groupByType.typeId}">Details</a></td>
            </tr>
        </c:forEach>
    </c:forEach>
</table>

</body>
</html>
