<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<html>

<common:head title="Catalog" />

<body>
    <div class="container">
        <common:header />

        <div class="col-md-8 col-md-offset-4">
            <h2>
                <fmt:message bundle="${loc}" key="local.catalog.name" />
            </h2>
            <form action="/pharmacy/controller" method="post">
                <input type="hidden" name="command" value="SEARCH_MEDICINES">
                <fmt:message bundle="${loc}" key="local.search.medicine.name" />:
                <input type="text" name="commercialName" value="" />
                <input type="submit" value="Enter" />
            </form>
        </div>
        <br />
        <div class="row justify-content-center">
            <div class="col-md-8">
                <table class="table table-hover table-bordered">
                    <thead class="thead-dark">
                        <tr>

                            <td>
                                <fmt:message bundle="${loc}" key="local.medicine.title" />
                            </td>
                            <td>
                                <fmt:message bundle="${loc}" key="local.producer" />
                            </td>
                            <td>
                                <fmt:message bundle="${loc}" key="local.packagePrice" />
                            </td>
                            <td>
                                <fmt:message bundle="${loc}" key="local.productBalance" />
                            </td>
                            <td>
                                <fmt:message bundle="${loc}" key="local.prescriptionRequired" />
                            </td>
                            <td>
                                <fmt:message bundle="${loc}" key="local.diseaseGroup" />
                            </td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="medicine" items="${medicines}">
                            <tr>
                                <td><a href="/pharmacy/controller?command=SHOW_MEDICINE_DETAILS&serialNumber=${medicine.serialNumber}">${medicine.commercialName}</a><br>
                                    ${medicine.internationalName}<br>
                                    ${medicine.medicineForm}&nbsp;${medicine.medicineDose} mg
                                </td>
                                <td>
                                    <c:out value="${medicine.producer.producerFactoryName}" /><br>
                                    <c:out value="${medicine.producer.producerCountry}" />
                                </td>
                                <td>
                                    <c:out value="${medicine.packagePrice}" /> руб
                                </td>
                                <td>
                                    <c:out value="${medicine.productBalance}" />
                                </td>
                                <td>
                                    <fmt:message bundle="${loc}" key="local.${medicine.prescriptionRequired}" />
                                </td>
                                <td>
                                    <fmt:message bundle="${loc}" key="local.${medicine.diseaseGroup}.disease.group" />
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div>

                <c:if test="${not empty analogueFind}">
                    <br><br><br>
                    <fmt:message bundle="${loc}" key="local.analogs" /> <br>
                    <table>
                        <tr>
                            <td>
                                <fmt:message bundle="${loc}" key="local.medicine.title" />
                            </td>
                            <td>
                                <fmt:message bundle="${loc}" key="local.producer" />
                            </td>
                            <td>
                                <fmt:message bundle="${loc}" key="local.packagePrice" />
                            </td>
                            <td>
                                <fmt:message bundle="${loc}" key="local.productBalance" />
                            </td>
                            <td>
                                <fmt:message bundle="${loc}" key="local.prescriptionRequired" />
                            </td>
                            <td>
                                <fmt:message bundle="${loc}" key="local.diseaseGroup" />
                            </td>
                        </tr>

                        <c:forEach var="medicine" items="${analogueFind}">
                            <tr>
                                <td><a href="/pharmacy/controller?command=SHOW_MEDICINE_DETAILS&serialNumber=${medicine.serialNumber}">${medicine.commercialName}</a>
                                    <c:out value="${medicine.internationalName}" />
                                    <c:out value="${medicine.medicineForm}" />
                                    <c:out value="${medicine.medicineDose}" />mg
                                </td>
                                <td>
                                    <c:out value="${medicine.producer.producerFactoryName}" />
                                    <c:out value="${medicine.producer.producerCountry}" />
                                </td>
                                <td>
                                    <c:out value="${medicine.packagePrice}" />
                                </td>
                                <td>
                                    <c:out value="${medicine.productBalance}" />
                                </td>
                                <td>
                                    <c:out value="${medicine.prescriptionRequired}" />
                                </td>
                                <td>
                                    <c:out value="${medicine.diseaseGroup}" />
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>

            </div>
</body>

</html>