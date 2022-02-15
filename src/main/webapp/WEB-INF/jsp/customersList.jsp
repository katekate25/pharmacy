<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/order" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>

<html >
    <common:head title="Orders" />
 <style>
body { background: url(img/medical-supplies-placed-on-a-blue.jpg);
      background-size:cover;
      margin: 0;

      }
       </style>
<body>
    <common:header />
    <div class="container">
        <br>
                <div class="row">
                    <div class="col-md-8 offset-md-1">
                        <form class="form-inline" action="/pharmacy/controller" method="post">
                            <input type="hidden" name="command" value="SHOW_CUSTOMERS">
                            <input class="form-control col-md-4 mr-sm-3 mb-2" type="text" name="client" value="" placeholder="Type client login"/>
                            <button class="btn btn-dark mb-2" type="submit">Search</button>
                        </form>
                    </div>
                </div>
                <br>
<c:if test="${not empty customerByLogin}">
        <table class="table table-hover table-bordered text-center">
            <thead class="thead-dark">
                <tr>
                  <th>
                        fullName
                    </th>
                    <th>
                        login
                    </th>

                    <th>
                       email
                    </th>
                     <th>
                       telNumber
                     </th>
                     <th>
                       age
                     </th>
                </tr>
            </thead>

            <tbody>

                                    <tr>
                                        <td>
                                            <c:out value="${customerByLogin.fullName}" />
                                        </td>
                                        <td>
                                            <c:out value="${customerByLogin.login}" /><br>
                                        </td>

                                        <td>
                                            <c:out value="${customerByLogin.email}" />
                                        </td>

                                        <td>
                                            <c:out value="${customerByLogin.telNumber}" />
                                        </td>
                                        <td>
                                            <c:out value="${customerByLogin.age}" />
                                        </td>
                                    </tr>
            <tbody>
<common:footer />
        </table>
        </c:if>

        <c:if test="${not empty customers}">
        <table class="table table-hover table-bordered text-center">
            <thead class="thead-dark">
                <tr>
                  <th>
                        fullName
                    </th>
                    <th>
                        login
                    </th>

                    <th>
                       email
                    </th>
                     <th>
                       telNumber
                     </th>
                     <th>
                       age
                     </th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="customer" items="${customers}">
                                    <tr>
                                        <td>
                                            <c:out value="${customer.fullName}" />
                                        </td>
                                        <td>
                                            <c:out value="${customer.login}" /><br>
                                        </td>

                                        <td>
                                            <c:out value="${customer.email}" />
                                        </td>

                                        <td>
                                            <c:out value="${customer.telNumber}" />
                                        </td>
                                        <td>
                                            <c:out value="${customer.age}" />
                                        </td>
                                    </tr>
                                </c:forEach>
            <tbody>
        </table>
        </c:if>
        <c:if test="${empty customers}">
            You do not have customers yet
        </c:if>

    </div>
    <common:footer />
</body>
</html>