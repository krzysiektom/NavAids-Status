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

<%@include file="../fragments/header.jspf" %>

<div class="container">

    <header>Pivot table</header>


    <div class="card mt-4">
        <div class="card-body">

            <table class="table table-hover">
                <tr>
                    <th colspan="3"></th>
                    <c:forEach items="${groups}" var="group">
                        <th colspan="3">
                            <a href="/devices/countByTypes/${group.id}">${group.abbr}</a></th>
                    </c:forEach>
                </tr>
                <tr>
                    <th>Superior</th>
                    <th>Owner</th>
                    <th>Airfield</th>
                    <c:forEach items="${groups}" var="group">
                        <th>Amount</th>
                        <th>Ready</th>
                        <th>Under service</th>
                    </c:forEach>
                </tr>
                <c:forEach items="${pivotTable}" var="pT">
                    <tr>
                        <td>
                            <a href="/devices/superior/${pT.airfield.owner.superior.id}">${pT.airfield.owner.superior.abbr}</a>
                        </td>
                        <td>
                            <a href="/devices/owner/${pT.airfield.owner.id}">${pT.airfield.owner.abbr}</a>
                        </td>
                        <td>
                            <a href="/devices/airfield/${pT.airfield.id}">${pT.airfield.abbr}</a>
                        </td>
                        <c:forEach items="${pT.devicesCountByAirfieldAndGroups}" var="device">
                            <td>${device.count}</td>
                            <td>${device.ready}</td>
                            <td>${device.underService}</td>
                        </c:forEach>
                    </tr>

                </c:forEach>
                <tr>
                    <th colspan="3"></th>
                    <c:forEach items="${sums}" var="sum">
                        <th>${sum.count}</th>
                        <th>${sum.ready}</th>
                        <th>${sum.underService}</th>
                    </c:forEach>
                </tr>

            </table>
        </div>
    </div>
</div>
</body>
</html>
