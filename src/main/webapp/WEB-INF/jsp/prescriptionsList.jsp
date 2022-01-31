<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/order" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>

<html >
    <common:head title="Prescriptions" />
 <style>
body { background: url(img/medical-supplies-placed-on-a-blue.jpg);
      background-size:cover;
      margin: 0;

      }
       </style>
<body>
    <div class="container">
        <common:header />

        <br>
        <div class="row">
            <div class="col-md-8 offset-md-1">
                <form class="form-inline" action="/pharmacy/controller" method="post">
                    <input type="hidden" name="command" value="SHOW_PRESCRIPTIONS">
                    <input class="form-control col-md-4 mr-sm-3 mb-2" type="text" name="client" value="" placeholder="Type patient login"/>
                    <button class="btn btn-dark mb-2" type="submit">Search</button>
                </form>
            </div>
        </div>
        <br>

<c:if test="${not empty foundPrescription}">
        <table class="table table-hover table-bordered text-center">
            <thead class="thead-dark">
                <tr>
                    <th>
                        prescriptionNumber
                    </th>
                    <th>
                        client
                    </th>
                    <th>
                        doctor
                    </th>
                    <th>
                        medicine
                        packageAmount
                    </th>
                    <th>
                       creationDate
                    </th>
                     <th>
                       expirationDate
                     </th>
                     <th>
                       orderNumber
                     </th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="prescription" items="${foundPrescription}">
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
                                            <c:out value="${prescription.medicine.medicineDose}" />mg<br>
                                            <c:out value="${prescription.medicine.serialNumber}" /><br>
                                            <c:out value="${prescription.medicine.producer.producerFactoryName}" /><br><br>
                                            <c:out value="${prescription.packageAmount}" />уп
                                        </td>

                                        <td>
                                            <c:out value="${prescription.creationDate}" />
                                        </td>
                                        <td>
                                            <c:out value="${prescription.expirationDate}" />
                                        </td>
                                        <td>
                                            <c:out value="${prescription.orderNumber}" />
                                        </td>
                                    </tr>
                                </c:forEach>
            <tbody>
<common:footer />
        </table>
        </c:if>

        <c:if test="${not empty prescriptionsList}">
        <table class="table table-hover table-bordered text-center">
            <thead class="thead-dark">
                <tr>
                    <th>
                        prescriptionNumber
                    </th>
                    <th>
                        client
                    </th>
                    <th>
                        doctor
                    </th>
                    <th>
                        medicine
                        packageAmount
                    </th>
                    <th>
                       creationDate
                    </th>
                     <th>
                       expirationDate
                     </th>
                     <th>
                       orderNumber
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
                                            <c:out value="${prescription.medicine.medicineDose}" />mg<br>
                                            <c:out value="${prescription.medicine.serialNumber}" /><br>
                                            <c:out value="${prescription.medicine.producer.producerFactoryName}" /><br><br>
                                            <c:out value="${prescription.packageAmount}" />уп
                                        </td>

                                        <td>
                                            <c:out value="${prescription.creationDate}" />
                                        </td>
                                        <td>
                                            <c:out value="${prescription.expirationDate}" />
                                        </td>
                                        <td>
                                            <c:out value="${prescription.orderNumber}" />
                                        </td>
                                    </tr>
                                </c:forEach>
            <tbody>
<common:footer />
        </table>
        </c:if>

    </div>
</body>
</html>