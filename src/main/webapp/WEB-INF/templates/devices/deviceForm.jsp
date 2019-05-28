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

<form:form method="post" modelAttribute="device">

    <div class="container">
        <header>Add type</header>

        <div class="card mt-4">
            <div class="card-body">

                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="nameId">Factory number:</label>
                        <form:input path="factoryNumber" class="form-control" id="nameId"/>
                        <form:errors path="factoryNumber" element="div" cssClass="error"/>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="typeId">Type:</label>
                        <form:select class="form-control" path="type.id" id="typeId">
                            <form:option value="" label="--Please Select--"/>
                            <form:options items="${types}" itemValue="id" itemLabel="name"/>
                        </form:select>
                        <form:errors path="type" element="div" cssClass="error"/>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="airfieldId">Airfield:</label>
                        <form:select class="form-control" path="airfield.id" id="airfieldId">
                            <form:option value="" label="--Please Select--"/>
                            <form:options items="${airfields}" itemValue="id" itemLabel="abbr"/>
                        </form:select>
                        <form:errors path="airfield" element="div" cssClass="error"/>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="ownerId">Owner:</label>
                        <form:select class="form-control" path="owner.id" id="ownerId">
                            <form:option value="" label="--Please Select--"/>
                            <form:options items="${owners}" itemValue="id" itemLabel="abbr"/>
                        </form:select>
                        <form:errors path="owner" element="div" cssClass="error"/>
                    </div>
                </div>

                <input class="btn btn-success btn-sm" type="submit" value="Save"/>

            </div>
        </div>
    </div>
</form:form>
</body>
</html>
