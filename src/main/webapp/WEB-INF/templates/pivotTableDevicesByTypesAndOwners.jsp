<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <script src="<c:url value="/webjars/jquery/3.0.0/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
    <title>allDevices</title>
    <title>pivotTable</title>
</head>
<body>

<%@include file="fragments/header.jspf" %>

<div class="container">

    <header>Pivot table</header>


    <div class="card mt-4">
        <div class="card-body">

            <table class="table table-hover">
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
        </div>
    </div>
</div>
</body>
</html>
