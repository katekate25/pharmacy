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
        <td>
        <form action = "/pharmacy/controller" method = "post">
             <input type="hidden" name="command" value="UPDATE_ENTRY_AMOUNT">
             <input type="hidden" name="entryId" value="${orderEntry.id}"/>
             <input type="number" name="amount" min="1" max="${Double.valueOf(orderEntry.medicine.productBalance).intValue()}" value="${Double.valueOf(orderEntry.packageAmount).intValue()}" />
             <input class="btn btn-dark" type="submit" value="Update" />
        </form>

        </td>
        <td>${orderEntry.medicine.packagePrice}руб</td>
        <td>
        <form action = "/pharmacy/controller" method = "post">
         <input type="hidden" name="command" value="DELETE_ENTRY">
         <input type="hidden" name="entryId" value="${orderEntry.id}"/>
         <button type="submit" class="btn btn-dark">${remove_button}</button>
        </form>
        </td>
        <td>

       <c:if test="${orderEntry.prescription != null}">
               ${orderEntry.prescription.prescriptionNumber}
               <br>
               <form action = "/pharmacy/controller" method = "post">
                    <input type="hidden" name="command" value="DELETE_PRESCRIPTION_FROM_ENTRY">
                    <input type="hidden" name="entryNumber" value="${orderEntry.id}">
                    <input type="hidden" name="prescrNumber" value="${orderEntry.prescription.prescriptionNumber}">
                    <p><button class="btn btn-dark" type="submit">Delete</button>
               </form>
       </c:if>

       <c:if test="${orderEntry.medicine.prescriptionRequired == true && orderEntry.prescription == null}">

               <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal_${orderEntry.id}">
                 Choose Prescription
               </button>


               <div class="modal fade" id="exampleModal_${orderEntry.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel_${orderEntry.id}" aria-hidden="true">
                 <div class="modal-dialog" role="document">
                   <div class="modal-content">
                     <div class="modal-header">
                       <h5 class="modal-title" id="exampleModalLabel_${orderEntry.id}">Prescriptions</h5>
                       <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                         <span aria-hidden="true">&times;</span>
                       </button>
                     </div>
                     <div class="modal-body">

                       <c:forEach var="availablePrescription" items="${availablePrescriptions}">

                           <c:if test="${orderEntry.medicine.internationalName eq availablePrescription.medicine.internationalName and orderEntry.packageAmount eq availablePrescription.packageAmount}">
                               <br>
                               <c:out value="${availablePrescription.prescriptionNumber}" /><br>
                               <c:out value="${availablePrescription.doctor.fullName}" /><br>
                               <c:out value="${availablePrescription.medicine.commercialName}" /><br>
                               <c:out value="${availablePrescription.medicine.medicineDose}" />mg<br>
                               <c:out value="${availablePrescription.packageAmount}" />уп<br>
                               exp <c:out value="${prescription.expirationDate}" /><br>
                               <form action = "/pharmacy/controller" method = "post">
                                    <input type="hidden" name="command" value="ADD_PRESCRIPTION_TO_ENTRY">
                                    <input type="hidden" name="entryNumber" value="${orderEntry.id}">
                                    <input type="hidden" name="prescrNumber" value="${availablePrescription.prescriptionNumber}">
                                    <p><button class="btn btn-dark" type="submit">Add</button>
                               </form>
                           </c:if>
                       </c:forEach>
                     </div>
                     <div class="modal-footer">
                       <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>


                     </div>
                   </div>
                 </div>
               </div>
                </c:if>
                <br>

                       <c:if test="${orderEntry.medicine.prescriptionRequired == true && orderEntry.prescription != null && orderEntry.packageAmount != orderEntry.prescription.packageAmount}">
                         Please, select ${orderEntry.prescription.packageAmount} package(s) as prescribed
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
   <input class"date" type="date" min="${minDeliveryDate}" max="${maxDeliveryDate}" value="${minDeliveryDate}" name="deliveryTime">
   <p><button class="btn btn-dark" type="submit" ${isReadyForPayment ? '' : 'disabled'}>Pay</button>
</form>

