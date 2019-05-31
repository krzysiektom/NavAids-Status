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

<form:form method="post" modelAttribute="owner">

    <div class="container">
        <header>Add owner</header>

        <div class="card mt-4">
            <div class="card-body">

                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="nameId">Name:</label>
                        <form:input path="name" class="form-control" id="nameId"/>
                        <form:errors path="name" element="div" cssClass="error"/>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="abbrId">Abbreviation:</label>
                        <form:input path="abbr" class="form-control" id="abbrId"/>
                        <form:errors path="abbr" element="div" cssClass="error"/>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="abbrId">Superior:</label>
                        <form:select class="form-control" path="superior.id" id="abbrId">
                            <form:option value="-" label="--Please Select--"/>
                            <form:options items="${superiors}" itemValue="id" itemLabel="abbr"/>
                        </form:select>
                        <form:errors path="superior" element="div" cssClass="error"/>
                    </div>
                </div>
                <input class="btn btn-success btn-sm" type="submit" value="Save"/>

            </div>
        </div>
    </div>
</form:form>
</body>
</html>
