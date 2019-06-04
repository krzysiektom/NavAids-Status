<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <script src="<c:url value="/webjars/jquery/3.0.0/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
    <title>allFailures</title>
</head>
<body>
<%@include file="../fragments/header.jspf" %>

<div class="container">

    <header>Failure page</header>


    <div class="card mt-4">
        <div class="card-body">

            <table class="table table-hover"><b>All devices:</b>
                <tr>

                    <th>Airfield</th>
                    <th>Owner</th>
                    <th>Group</th>
                    <th>Type</th>
                    <th>Created</th>
                    <th style="width: 15%">Action</th>
                </tr>
                <c:forEach items="${allFailures}" var="failure">
                    <tr>
                        <td>${failure.device.airfield.abbr}</td>
                        <td>${failure.device.owner.abbr}</td>
                        <td>${failure.device.type.group.abbr}</td>
                        <td>${failure.device.type.name}</td>
                        <td>${failure.created.format(format)}</td>
                        <td><a href="/failures/${failure.id}" class="btn btn-warning btn-sm">Details</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
