<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <script src="<c:url value="/webjars/jquery/3.0.0/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
    <title>Main page</title>
</head>
<body>
<%@include file="fragments/header.jspf" %>

<div class="container">

    <header>Main page</header>


    <div class="card mt-4">
        <div class="card-body">

            <table class="table table-hover">
                <tr>
                    <th colspan="3"></th>
                    <c:forEach items="${groups}" var="group">
                        <th colspan="3" class="text-center">${group.abbr}</th>
                    </c:forEach>
                </tr>
                <tr>
                    <th class="text-center">Superior</th>
                    <th class="text-center">Owner</th>
                    <th class="text-center">Airfield</th>
                    <c:forEach items="${groups}" var="group">
                        <th class="text-center">Amount</th>
                        <th class="text-center">Ready</th>
                        <th class="text-center">Under service</th>
                    </c:forEach>
                </tr>
                <c:forEach items="${pivotTable}" var="pT">
                    <tr>
                        <td class="text-center">${pT.airfield.owner.superior.abbr}</td>
                        <td class="text-center">${pT.airfield.owner.abbr}</td>
                        <td class="text-center">${pT.airfield.abbr}</td>
                        <c:forEach items="${pT.devicesCountByAirfieldAndGroups}" var="device">
                            <td class="text-center">${device.count}</td>
                            <td class="text-center">${device.ready}</td>
                            <td class="text-center">${device.underService}</td>
                        </c:forEach>
                    </tr>

                </c:forEach>
                <tr>
                    <th colspan="3" class="text-right">Total</th>
                    <c:forEach items="${sums}" var="sum">
                        <th class="text-center">${sum.count}</th>
                        <th class="text-center">${sum.ready}</th>
                        <th class="text-center">${sum.underService}</th>
                    </c:forEach>
                </tr>

            </table>
        </div>
    </div>
</div>

</body>
</html>
