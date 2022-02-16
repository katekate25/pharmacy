<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.add.button" var="add_button" />

<html>
<common:head title="Prescription" />
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
      <h4 class="card-title mt-3 text-center"><fmt:message bundle="${loc}" key="local.prescription" /></h4>

      <form action="/pharmacy/controller" method="post">

      <input type="hidden" name="command" value="ADD_PRESCRIPTION_TO_DB" />

        <div class="form-group">
          <fmt:message bundle="${loc}" key="local.prescription.patient" /><br>
          <input name="client" class="form-control" type="text" >
        </div>
<input type="hidden" name="serialNumber" value="${serialNumber}">
<input type="hidden" name="doctor" value="${login}" />

        <div class="form-group">
                 <fmt:message bundle="${loc}" key="local.prescribed.amount" /><br>
                <input name="packageAmount" class="form-control"type="text" >
              </div>

              <div class="form-group">
                 <fmt:message bundle="${loc}" key="local.usage.instruction" /><br>
                <input name="usageInstruction" class="form-control" type="text" >
              </div>
<div class="form-group">

          <fmt:message bundle="${loc}" key="local.prescription.creation.date" /><br>
          <input name="creationDate" class="form-control" type="text" >
        </div>


        <div class="form-group">
           <fmt:message bundle="${loc}" key="local.prescription.expiration.date" /><br>
          <input name="expirationDate" class="form-control" type="text" >
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