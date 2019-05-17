<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <script src="<c:url value="/webjars/jquery/3.0.0/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
    <title>fixForm</title>
</head>
<body>

<%@include file="../fragments/header.jspf" %>

<div class="container">
    <header>Failure page"</header>


    <div class="card mt-4">
        <div class="card-body">

            <form:form method="post" modelAttribute="fix">
                <form:errors path="done"/><br>
                Done: <form:input path="done"/><br>
                <form:errors path="usedMaterials"/><br>
                Used materials: <form:input path="usedMaterials"/><br>
                <input type="submit" value="Save"/>
            </form:form>

        </div>
    </div>
</div>

</body>
</html>
