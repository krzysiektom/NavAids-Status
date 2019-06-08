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

<form:form method="post">

    <div class="container">
        <header>Change password</header>

        <div class="card mt-4">
            <div class="card-body">

                <c:if test="${error != false}">
                    <a element="div" cssClass="error">${errorMsg}</a>
                </c:if>

                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="current_password">Current password:</label>
                        <input type="password" name="currentPassword" class="form-control" id="current_password"/>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="new_password">New password:</label>
                        <input type="password" name="newPassword" class="form-control" id="new_password"/>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="confirm_password">Confirm password:</label>
                        <input type="password" name="confirmPassword" class="form-control" id="confirm_password"/>
                    </div>
                </div>
                <a href="/users/details" class="btn btn-primary btn-sm" >Close</a>
                <input type="submit" class="btn btn-success btn-sm" value="Save"/>
            </div>
        </div>
    </div>
</form:form>


</body>
</html>
