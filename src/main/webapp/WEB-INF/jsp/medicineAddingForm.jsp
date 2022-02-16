<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<html>
<common:head title="Add medicine" />
 <style>
body { background: url(img/medical-supplies-placed-on-a-blue.jpg);
      background-size:cover;
      margin: 0;

      }
       </style>
<body>
    <common:header />



     <section class="pt-5 pb-5 mt-0 align-items-center d-flex" style="min-height: 100vh; background-size: cover;">
                    <div class="container-fluid">
                      <div class="row  justify-content-center align-items-center d-flex-row text-center h-100">
                        <div class="col-12 col-md-4 col-lg-3   h-50 ">
                          <div class="card shadow">
                            <div class="card-body mx-auto">
                              <h4 class="card-title mt-3 text-center"><fmt:message bundle="${loc}" key="local.medicine.form" /></h4>

                              <c:if test="${param['successfulAdding']}">
                                          <fmt:message bundle="${loc}" key="local.adding.successfull" />
                                      </c:if>

                              <form action="/pharmacy/controller" method="post">

                              <input type="hidden" name="command" value="ADD_MEDICINE_TO_DB" />
                                <div class="form-group">
                                  <fmt:message bundle="${loc}" key="local.commercialName" /><br>
                                  <input name="commercialName" class="form-control" type="text" >
                                </div>

                                <div class="form-group">

                                        <fmt:message bundle="${loc}" key="local.internationalName" /><br>
                                        <input name="internationalName" class="form-control"type="text" >
                                      </div>
                                      <div class="form-group">
                                                                         <fmt:message bundle="${loc}" key="local.medicineForm" /><br>
                                                                        <input name="medicineForm" class="form-control" type="text" >
                                                                      </div>
<div class="form-group">

                                  <fmt:message bundle="${loc}" key="local.medicineDose" /><br>
                                  <input name="medicineDose" class="form-control" type="text" >
                                </div>
                                <div class="form-group">

                                                                   <fmt:message bundle="${loc}" key="local.productArrival" /><br>
                                                                  <input name="productArrival" class="form-control" type="text" >
                                                                </div>
                 <div class="form-group">

                                                   <fmt:message bundle="${loc}" key="local.diseaseGroup" /><br>
                                                   <input name="diseaseGroup" class="form-control" type="text" >
                                                 </div>
                <div class="form-group">

                                                  <fmt:message bundle="${loc}" key="local.arrivalDate" /><br>
                                                  <input name="arrivalDate" class="form-control" type="text" >
                                                </div>
                <div class="form-group">
                                                  <fmt:message bundle="${loc}" key="local.invoiceNumber" /><br>
                                                  <input name="invoiceNumber" class="form-control" type="text" >
                                                </div>
                <div class="form-group">

                                                  <fmt:message bundle="${loc}" key="local.expirationDate" /><br>
                                                  <input name="medicineExpirationDate" class="form-control" type="text" >
                                                </div>
               <div class="form-group">

                                                 <fmt:message bundle="${loc}" key="local.prescriptionRequired" /><br>
                                                 <input name="prescriptionRequired" class="form-control" type="text" >
                                               </div>
               <div class="form-group">

                                                 <fmt:message bundle="${loc}" key="local.packagePrice" /><br>
                                                 <input name="packagePrice" class="form-control" type="text">
                                               </div>
              <div class="form-group">

                                                <fmt:message bundle="${loc}" key="local.producer" /><br>
                                                <input name="producerName" class="form-control" type="text">
                                              </div>
              <div class="form-group">

                                                 <fmt:message bundle="${loc}" key="local.serialNumber" /><br>
                                                <input name="serialNumber" class="form-control" type="text" required>
                                              </div>

                             <div class="row">
                              <button type="submit" class="btn btn-primary btn-block"> <fmt:message bundle="${loc}" key="local.add.button" /> </button>
                            </div>
                            </form>




                            </div>
                          </div>
                        </div>
                      </div>
                    </div>

                 </section>

    <common:footer />
</body>

</html>