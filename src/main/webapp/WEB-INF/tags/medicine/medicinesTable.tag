<%@ tag trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ attribute name="medicineList" required="true" type="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>

<div class="row justify-content-center">
    <div class="col-md-10">
        <table class="table table-hover table-bordered text-center">
            <thead class="thead-dark">
                <tr>
                    <th>
                        <fmt:message bundle="${loc}" key="local.medicine.title" />
                    </th>
                    <th>
                        <fmt:message bundle="${loc}" key="local.producer" />
                    </th>
                    <th>
                        <fmt:message bundle="${loc}" key="local.packagePrice" />
                    </th>
                    <th>
                        <fmt:message bundle="${loc}" key="local.productBalance" />
                    </th>
                    <th>
                        <fmt:message bundle="${loc}" key="local.prescriptionRequired" />
                    </th>
                    <th>
                        <fmt:message bundle="${loc}" key="local.diseaseGroup" />
                    </th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="medicine" items="${medicineList}">
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
                            <c:out value="${medicine.packagePrice}" />$
                        </td>
                        <td>
                            <c:out value="${medicine.productBalance}" />
                        </td>
                        <td class="${medicine.prescriptionRequired ? 'text-danger' : 'text-success'} font-weight-bold">
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
</div>