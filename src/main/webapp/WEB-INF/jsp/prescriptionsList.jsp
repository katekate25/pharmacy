<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/order" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.type.login" var="customer" />

<html >
    <common:head title="Prescriptions" />
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
        <c:if test="${user.userRole.name() eq 'PHARMACIST'}">
        <div class="row">
            <div class="col-md-8 offset-md-1">
                <form class="form-inline" action="/pharmacy/controller" method="post">
                    <input type="hidden" name="command" value="GO_TO_PRESCRIPTIONS_LIST">
                    <input class="form-control col-md-4 mr-sm-3 mb-2" type="text" name="client" value="" placeholder="${customer}"/>
                    <button class="btn btn-dark mb-2" type="submit"><fmt:message bundle="${loc}" key="local.search.button" /></button>
                </form>
            </div>
        </div>
        <br>
<c:if test="${empty prescriptionsList}">
Nothing found
</c:if>

        <c:if test="${not empty prescriptionsList}">
        <table class="table table-hover table-bordered text-center">
            <thead class="thead-dark">
                <tr>
                    <th>
                        <fmt:message bundle="${loc}" key="local.prescription.number" />
                    </th>
                    <th>
                        <fmt:message bundle="${loc}" key="local.customer" />
                    </th>
                    <th>
                        <fmt:message bundle="${loc}" key="local.doctor" />
                    </th>
                    <th>
                        <fmt:message bundle="${loc}" key="local.medicine.title" />

                    </th>
                    <th>
                       <fmt:message bundle="${loc}" key="local.prescription.creation.date" />
                    </th>
                     <th>
                       <fmt:message bundle="${loc}" key="local.prescription.expiration.date" />
                     </th>
                     <th>
                       <fmt:message bundle="${loc}" key="local.prescription.status" />
                     </th>

                </tr>
            </thead>

            <tbody>
                <c:forEach var="prescription" items="${prescriptionsList}">
                                    <tr>
                <td>
                    <c:out value="${prescription.prescriptionNumber}" />
                </td>
                <td>
                    <c:out value="${prescription.client.fullName}" /><br>
                    <c:out value="${prescription.client.login}" />
                </td>

                <td>
                    <c:out value="${prescription.doctor.fullName}" />
                    <br>
                    <c:out value="${prescription.doctor.login}" />
                </td>
                <td>
                    <c:out value="${prescription.medicine.commercialName}" /><br>
                    <c:out value="${prescription.medicine.internationalName}" /><br>
                    <c:out value="${prescription.medicine.medicineForm}" />&nbsp
                    <c:out value="${prescription.medicine.medicineDose}" /><fmt:message bundle="${loc}" key="local.mg" /><br>
                    <c:out value="${prescription.medicine.serialNumber}" /><br>
                    <c:out value="${prescription.medicine.producer.producerFactoryName}" /><br><br>
                    <c:out value="${prescription.packageAmount}" /><fmt:message bundle="${loc}" key="local.package" />
                </td>

                <td>
                    <c:out value="${prescription.creationDate}" />
                </td>
                <td>
                    <c:out value="${prescription.expirationDate}" />
                </td>
                <td>
                    <c:out value="${prescription.status}" />
                </td>
            </tr>
                                </c:forEach>
            <tbody>
<common:footer />
        </table>
        </c:if>
</c:if>

<c:if test="${user.userRole.name() eq 'CUSTOMER'}">
<c:if test="${not empty prescriptions}">
        <table class="table table-hover table-bordered text-center">
            <thead class="thead-dark">
                <tr>
                    <th>
                    <fmt:message bundle="${loc}" key="local.prescription.number" />
                </th>
                <th>
                    <fmt:message bundle="${loc}" key="local.customer" />
                </th>
                <th>
                    <fmt:message bundle="${loc}" key="local.doctor" />
                </th>
                <th>
                    <fmt:message bundle="${loc}" key="local.medicine.title" />

                </th>
                <th>
                   <fmt:message bundle="${loc}" key="local.prescription.creation.date" />
                </th>
                 <th>
                   <fmt:message bundle="${loc}" key="local.prescription.expiration.date" />
                 </th>



                </tr>
            </thead>

            <tbody>
                <c:forEach var="prescription" items="${prescriptions}">
                                    <tr>
                <td>
                    <c:out value="${prescription.prescriptionNumber}" />
                </td>
                <td>
                    <c:out value="${prescription.client.fullName}" /><br>
                    <c:out value="${prescription.client.login}" />
                </td>

                <td>
                    <c:out value="${prescription.doctor.fullName}" />
                    <br>
                    <c:out value="${prescription.doctor.login}" />
                </td>
                <td>
                    <c:out value="${prescription.medicine.commercialName}" /><br>
                    <c:out value="${prescription.medicine.internationalName}" /><br>
                    <c:out value="${prescription.medicine.medicineForm}" />&nbsp
                    <c:out value="${prescription.medicine.medicineDose}" />mg<fmt:message bundle="${loc}" key="local.mg" /><br>
                    <c:out value="${prescription.medicine.serialNumber}" /><br>
                    <c:out value="${prescription.medicine.producer.producerFactoryName}" /><br><br>
                    <c:out value="${prescription.packageAmount}" /><fmt:message bundle="${loc}" key="local.package" />
                </td>

                <td>
                    <c:out value="${prescription.creationDate}" />
                </td>
                <td>
                    <c:out value="${prescription.expirationDate}" />
                </td>

                                    </tr>
                                </c:forEach>
            <tbody>
        </table>
        </c:if>
        </c:if>
    </div>
    <common:footer />
</body>
</html>