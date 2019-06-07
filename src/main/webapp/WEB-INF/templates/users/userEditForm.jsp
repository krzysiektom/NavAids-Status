<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <script src="<c:url value="/webjars/jquery/3.0.0/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
    <title>userForm</title>
</head>
<body>

<%@include file="../fragments/header.jspf" %>

<form:form method="post" modelAttribute="user">

    <div class="container">
        <header>Edit user</header>

        <div class="card mt-4">
            <div class="card-body">

                <c:if test="${error != false}">
                    <a element="div" cssClass="error">${errorMsg}</a>
                </c:if>

                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="firstNameId">firstName:</label>
                        <form:input path="firstName" class="form-control" id="firstNameId"/>
                        <form:errors path="firstName" element="div" cssClass="error"/>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="lastNameId">lastName:</label>
                        <form:input path="lastName" class="form-control" id="lastNameId"/>
                        <form:errors path="lastName" element="div" cssClass="error"/>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="emailId">email:</label>
                        <form:input path="email" class="form-control" id="emailId"/>
                        <form:errors path="email" element="div" cssClass="error"/>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-4">
                        <form:errors path="password" element="div" cssClass="error"/>
                    </div>

                </div>
                <input type="submit" class="btn btn-success btn-sm" value="Save"/>

            </div>
        </div>
    </div>
</form:form>





</body>
</html>
