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

    <header>Type page</header>


    <div class="card mt-4">
        <div class="card-body">

            <table class="table table-hover">
                <b>All types:</b>
                <a href="/types/add" class="btn btn-primary btn-sm float-right">Add type</a>
                <tr>
                    <th>Name</th>
                    <th>Abbr group</th>
                    <th>Action</th>
                </tr>
                <c:forEach items="${types}" var="type">
                    <tr>
                        <td>${type.name}</td>
                        <td>${type.group.abbr}</td>
                        <td>
                            <a href="/devices/type/${type.id}" class="btn btn-success btn-sm">All devices</a>
                            <a href="/types/edit/${type.id}" class="btn btn-primary btn-sm">Edit</a>
                            <a href="/types/delete/${type.id}" class="btn btn-warning btn-sm">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
