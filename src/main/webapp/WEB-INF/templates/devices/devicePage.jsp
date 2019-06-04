<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <script src="<c:url value="/webjars/jquery/3.0.0/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
    <title>devicePage</title>
</head>
<body>

<%@include file="../fragments/header.jspf" %>

<div class="container">

    <header>
        Device
        <a href="/devices/edit/${device.id}" class="btn btn-primary btn-sm float-right">Edit</a>

        <c:if test="${!device.ready}">
            <a href="/failures/device/${device.id}" class="btn btn-warning btn-sm float-right">Failure details</a>
        </c:if>
    </header>

    <div class="card mt-4">
        <div class="card-body">

            <table class="table table-hover">
                <tr>
                <tr>
                    <th>Group</th>
                    <th>Type</th>
                    <th>Status</th>
                    <th>Factory Number</th>
                    <th>Owner</th>
                    <th>Airfield</th>
                </tr>
                </tr>
                <td>${device.type.group.abbr}</td>
                <td>${device.type.name}</td>
                <td>${device.ready}</td>
                <td>${device.factoryNumber}</td>
                <td>${device.owner.abbr}</td>
                <td>${device.airfield.abbr}</td>
            </table>
        </div>
    </div>
</div>

</body>
</html>
