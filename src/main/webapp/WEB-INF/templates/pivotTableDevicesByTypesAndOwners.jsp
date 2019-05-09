<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>pivotTable</title>
</head>
<body>
<table><b>Pivot table</b>
    <tr>
        <th colspan="3"></th>
        <c:forEach items="${groups}" var="group">
            <th colspan="2">"${group.abbr}"</th>
        </c:forEach>

    </tr>
    <tr>
        <th>Superior</th>
        <th>Owner</th>
        <th>Airfield</th>
        <c:forEach items="${groups}" var="group">
            <th>Amount</th>
            <th>Ready</th>
        </c:forEach>
    </tr>
    <c:forEach items="${superiors}" var="superior">
        <c:forEach items="${deviceService.findAllOwnersBySuperior(superior)}" var="owner">
            <tr>
                <td>${owner.superior.abbr}</td>
                <td>${owner.abbr}</td>
                <td>${deviceService.findAirfieldByOwner(owner).abbr}</td>
                <c:forEach items="${groups}" var="group">
                    <td>${deviceService.countByOwnerAndGroup(owner,group)}</td>
                    <td>${deviceService.countByOwnerAndGroupAndReadyTrue(owner,group)}</td>
                </c:forEach>
            </tr>
        </c:forEach>
    </c:forEach>
</table>
</body>
</html>
