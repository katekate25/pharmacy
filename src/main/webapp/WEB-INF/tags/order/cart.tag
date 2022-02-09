<%@ tag trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ attribute name="order" required="true" type="com.epam.training.epharmacy.entity.Order"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<fmt:message bundle="${loc}" key="local.delete" var="remove_button" />

<br> <br> <br>

<table class="table table-hover table-bordered text-center">
<thead class="thead-dark">
    <tr>
        <th><fmt:message bundle="${loc}" key="local.prescribed.medicine" /></th>
        <th><fmt:message bundle="${loc}" key="local.prescribed.amount" /></th>
        <th><fmt:message bundle="${loc}" key="local.packagePrice" /></th>
        <th></th>
        <th>Prescription</th>
    </tr>
    </thead>
    <tbody>
<c:forEach var ="orderEntry" items="${order.orderEntries}">

    <tr>
        <td>${orderEntry.medicine.commercialName}</td>
        <td>${orderEntry.packageAmount}</td>
        <td>${orderEntry.medicine.packagePrice}руб</td>
        <td>
        <form action = "/pharmacy/controller" method = "post">
         <input type="hidden" name="command" value="DELETE_ENTRY">
         <input type="hidden" name="entryId" value="${orderEntry.id}"/>
         <button type="submit" class="btn btn-dark">${remove_button}</button>
        </form>
        </td>
        <td>
        <c:if test="${orderEntry.medicine.prescriptionRequired eq 'true'}">
        <c:set var = "serialNumber" value = "${orderEntry.medicine.serialNumber}"/>

             <c:forEach var="validPrescription" items="${validPrescriptions}">
            <c:out value="${validPrescription.prescriptionNumber}" /><br>
              <form action = "/pharmacy/controller" method = "post">
                 <input type="hidden" name="command" value="ADD_PRESCRIPTION_TO_ENTRY">
                 <input type="hidden" name="entryNumber" value="${orderEntry.id}">
                 <input type="hidden" name="prescrNumber" value="${validPrescription.prescriptionNumber}">
                 <p><button class="btn btn-dark" type="submit">Add</button>
              </form>

             </c:forEach>

        </c:if>
        </td>
    </tr>
</c:forEach>
    <tr>
        <td></td><td></td><td><fmt:message bundle="${loc}" key="local.totalPrice" />: <fmt:formatNumber type="number" maxFractionDigits="3" value="${order.totalPrice}"/></td><td></td><td></td>
    </tr>
    </tbody>
</table>








<form action = "/pharmacy/controller" method = "post">
   <input type="hidden" name="command" value="PAY_ORDER">
   <p><fmt:message bundle="${loc}" key="local.choose.deliveryTime" /></p>
   <input type="date" name="deliveryTime">

   <p><button class="btn btn-dark" type="submit">Pay</button>
</form>

