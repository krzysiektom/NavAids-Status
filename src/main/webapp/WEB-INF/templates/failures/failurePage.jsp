<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <script src="<c:url value="/webjars/jquery/3.0.0/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
    <title>failurePage</title>
</head>
<body>
<%@include file="../fragments/header.jspf" %>

<div class="container">

    <header>Failure page"</header>


    <div class="card mt-4">
        <div class="card-body">

            <table class="table table-hover"><b>Device details</b>
                <tr>

                    <th>Airfield</th>
                    <th>Owner</th>
                    <th>Group</th>
                    <th>Type</th>
                    <th>Factory number</th>
                </tr>
                <tr>
                    <td>${failure.device.airfield.abbr}</td>
                    <td>${failure.device.owner.abbr}</td>
                    <td>${failure.device.type.group.abbr}</td>
                    <td>${failure.device.type.name}</td>
                    <td>${failure.device.factoryNumber}</td>
                </tr>
            </table>
        </div>
    </div>

    <div class="card mt-4">
        <div class="card-body">

            <table class="table table-hover"><b>Failure details</b>
                <tr>

                    <th>Description</th>
                    <th>Created by</th>
                    <th>Created</th>
                </tr>
                <tr>
                    <td>${failure.description}</td>
                    <td>${failure.user.firstName} ${failure.user.lastName}</td>
                    <td>${failure.created}</td>
                </tr>
            </table>
        </div>
    </div>

    <div class="card mt-4">
        <div class="card-body">

            <table class="table table-hover"><b>Fixes details:</b>
                <tr>

                    <th>Done</th>
                    <th>Used materials</th>
                    <th>Created</th>
                    <th>Action</th>
                </tr>
                <c:forEach items="${allFixes}" var="fix">
                    <tr>
                        <td>${fix.done}</td>
                        <td>${fix.usedMaterials}</td>
                        <td>${fix.created}</td>
                        <td><a href="#details${fix.id}" class="btn btn-success btn-sm" data-toggle="collapse">Details</a></td>
                    </tr>
                    <tr>
                        <td colspan="4" id="details${fix.id}" class="collapse" >
                                ${fix.id}
                            Lorem ipsum dolor sit amet, consectetur adipisicing elit,
                            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                            quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>

</body>
</html>
