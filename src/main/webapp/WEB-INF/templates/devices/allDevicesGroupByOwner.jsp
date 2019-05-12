<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <script src="<c:url value="/webjars/jquery/3.0.0/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
    <title>allDevicesGroupByOwner</title>
</head>
<body>

<%@include file="../fragments/header.jspf" %>

<div class="container">

    <header>All devices group by owner</header>

    <div class="card mt-4">
        <div class="card-body">

            <table class="table table-hover">
                <tr>
                    <th>Type</th>
                    <th>Status</th>
                    <th style="width: 15%">Actions</th>
                </tr>
                <c:forEach items="${groupByOwners}" var="groupByOwner">
                    <tr>
                        <td colspan="3">${groupByOwner.owner.abbr}</td>
                    </tr>
                    <c:forEach items="${groupByOwner.devices}" var="device">
                        <tr>
                            <td>${device.type.name}</td>
                            <td>${device.ready}</td>
                            <td><a href="/devices/${device.id}" class="btn btn-success">Details</a></td>
                        </tr>
                    </c:forEach>
                </c:forEach>
            </table>
        </div>
    </div>
</div>


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
