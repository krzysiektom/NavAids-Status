<%--
  Created by IntelliJ IDEA.
  User: krzysztof
  Date: 12.05.19
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <script src="<c:url value="/webjars/jquery/3.0.0/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
    <title>devicesByGroup</title>
</head>
<body>
<%@include file="../fragments/header.jspf" %>

<div class="container">

    <header>All devices group by ${group.abbr}</header>

    <div class="card mt-4">
        <div class="card-body">

            <table class="table table-hover">
                <tr>
                    <th>Airfield</th>
                    <th>Status</th>
                    <th style="width: 15%">Actions</th>
                </tr>
                <c:forEach items="${devicesByGroups}" var="devicesByGroup">
                    <tr>
                        <td colspan="5">${devicesByGroup.type.name}</td>
                    </tr>
                    <c:forEach items="${devicesByGroup.devices}" var="device">

                        <tr>
                            <td>${device.airfield.abbr}</td>
                            <td>${device.ready}</td>
                            <td><a href="/devices/${device.id}" class="btn btn-success btn-sm">Details</a></td>
                        </tr>
                    </c:forEach>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
