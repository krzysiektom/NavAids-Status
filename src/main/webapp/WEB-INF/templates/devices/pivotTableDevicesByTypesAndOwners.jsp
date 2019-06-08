<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <script src="<c:url value="/webjars/jquery/3.0.0/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/4.3.1/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/webjars/bootstrap/4.3.1/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
    <title>allDevices</title>
    <title>pivotTable</title>
</head>
<body>

<%@include file="../fragments/header.jspf" %>

<div class="container">

    <header>Main page</header>


    <div class="card mt-4">
        <div class="card-body">

            <table class="table table-hover">
                <tr>
                    <th colspan="3"></th>
                    <c:forEach items="${groups}" var="group">
                        <th colspan="3" class="text-center">
                            <a href="/devices/countByTypes/${group.id}" class="text-center">${group.abbr}</a></th>
                    </c:forEach>
                </tr>
                <tr>
                    <th class="text-center">Superior</th>
                    <th class="text-center">Owner</th>
                    <th class="text-center">Airfield</th>
                    <c:forEach items="${groups}" var="group">
                        <th class="text-center">Amount</th>
                        <th class="text-center">Ready</th>
                        <th class="text-center">Under service</th>
                    </c:forEach>
                </tr>
                <c:forEach items="${pivotTable}" var="pT">
                    <tr>
                        <td class="text-center">
                            <a href="/devices/superior/${pT.airfield.owner.superior.id}">${pT.airfield.owner.superior.abbr}</a>
                        </td>
                        <td class="text-center">
                            <a href="/devices/owner/${pT.airfield.owner.id}">${pT.airfield.owner.abbr}</a>
                        </td>
                        <td class="text-center">
                            <a href="/devices/airfield/${pT.airfield.id}">${pT.airfield.abbr}</a>
                        </td>
                        <c:forEach items="${pT.devicesCountByAirfieldAndGroups}" var="device">
                            <td class="text-center">
                                <c:if test="${device.count!=0}">
                                    <a href="/devices/detail/${device.group.id}/${device.airfield.id}">${device.count}</a>
                                </c:if>
                                <c:if test="${device.count==0}">
                                    <a>${device.count}</a>
                                </c:if>
                            </td>
                            <td class="text-center">
                                <c:if test="${device.ready!=0}">
                                    <a href="/devices/detail/ready/${device.group.id}/${device.airfield.id}">${device.ready}</a>
                                </c:if>
                                <c:if test="${device.ready==0}">
                                    <a>${device.ready}</a>
                                </c:if>
                            </td>
                            <td class="text-center">
                                <c:if test="${device.underService!=0}">
                                    <a href="/devices/detail/under/${device.group.id}/${device.airfield.id}">${device.underService}</a>
                                </c:if>
                                <c:if test="${device.underService==0}">
                                    <a>${device.underService}</a>
                                </c:if>
                            </td>
                        </c:forEach>
                    </tr>

                </c:forEach>
                <tr>
                    <th colspan="3" class="text-right">Total</th>
                    <c:forEach items="${sums}" var="sum">
                        <th class="text-center">
                            <a href="/devices/total/${sum.group.id}">${sum.count}</a>
                        </th>
                        <th class="text-center">
                            <a href="/devices/total/ready/${sum.group.id}">${sum.ready}</a>
                        </th>
                        <th class="text-center">
                            <c:if test="${sum.underService!=0}">
                                <a href="/devices/total/under/${sum.group.id}">${sum.underService}</a>
                            </c:if>
                            <c:if test="${sum.underService==0}">
                                <a>${sum.underService}</a>
                            </c:if>
                        </th>
                    </c:forEach>
                </tr>

            </table>
        </div>
    </div>
</div>
</body>
</html>
