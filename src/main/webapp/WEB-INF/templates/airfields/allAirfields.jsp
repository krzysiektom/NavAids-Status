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

    <header>Airfield page</header>


    <div class="card mt-4">
        <div class="card-body">

            <table class="table table-hover"><b>All airfields:</b>
                <tr>
                    <th>Name</th>
                    <th>Abbr name</th>
                    <th>Abbr superior name</th>
                </tr>
                <c:forEach items="${airfields}" var="airfield">
                    <tr>
                        <td>${airfield.name}</td>
                        <td>${airfield.abbr}</td>
                        <td>${airfield.owner.abbr}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
