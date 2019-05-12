<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <script src="<c:url value="/webjars/jquery/3.0.0/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
    <title>Home</title>
</head>
<body>
<%@include file="fragments/header.jspf" %>

<div class="container">

    <header>Devices</header>


    <div class="card mt-4">
        <div class="card-body">

            <a href="/devices/">All devices</a><br>
            <a href="/devices/groupByType">All devices group by type</a><br>
            <a href="/devices/groupByOwner">All devices group by owner</a><br>
            <a href="/devices/groupByAirfield">All devices group by airfield</a><br>
            <a href="/devices/groupByTypeOrderByGroup">All devices group by type order by group</a><br>
            <a href="/devices/pivotTable">Pivot table</a><br>
            <a href="/failures/">All failure</a><br>
        </div>
    </div>
</div>

</body>
</html>
