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

    <header>Group page</header>


    <div class="card mt-4">
        <div class="card-body">

            <table class="table table-hover">
                <b>All groups:</b>
                <a href="/groups/add" class="btn btn-primary btn-sm float-right">Add group</a>
                <tr>

                    <th>Name</th>
                    <th>Abbr</th>
                    <th>Action</th>
                </tr>
                <c:forEach items="${groups}" var="group">
                    <tr>
                        <td>${group.name}</td>
                        <td>${group.abbr}</td>
                        <td>
                            <a href="/types/all/${group.id}" class="btn btn-success btn-sm">Types</a>
                            <a href="/groups/edit/${group.id}" class="btn btn-primary btn-sm">Edit</a>
                            <a href="/groups/delete/${group.id}" class="btn btn-warning btn-sm">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
