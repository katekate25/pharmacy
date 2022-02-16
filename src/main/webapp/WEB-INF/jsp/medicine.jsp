<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.add.button" var="add" />

<html>
<common:head title="Medicine" />
 <style>
body { background: url(img/medical-supplies-placed-on-a-blue.jpg);
      background-size:cover;
      margin: 0;

      }
      .forecast {
      width: 50%;
          margin: auto;
          padding: .3rem;
          background-color: #eee;

      }

      .forecast > h1,
      .day-forecast {
          margin: .5rem;
          padding: .3rem;
          font-size: 3rem;
          text-align: center;
      }

      .day-forecast {
          background: right/contain content-box border-box no-repeat
              url('/media/examples/rain.svg') white;
      }

      .day-forecast > h2,
      .day-forecast > p {
          margin: .2rem;
          font-size: 1rem;
      }

       </style>
<body>
    <common:header />
    <div class="container">
<br>

<article class="forecast">
    <h1><fmt:message bundle="${loc}" key="local.prescribed.medicine" /></h1>
    <article class="day-forecast">
        <h2><fmt:message bundle="${loc}" key="local.commercialName" /></h2>
        <p>${medicineBySeries.commercialName}</p>
    </article>
    <article class="day-forecast">
        <h2><fmt:message bundle="${loc}" key="local.internationalName" /></h2>
        <p><c:out value="${medicineBySeries.internationalName}" /></p>
    </article>
    <article class="day-forecast">
        <h2> <fmt:message bundle="${loc}" key="local.medicineForm" /></h2>
        <p><c:out value="${medicineBySeries.medicineForm}" /></p>
    </article>
    <article class="day-forecast">
            <h2><fmt:message bundle="${loc}" key="local.medicineDose" /></h2>
            <p><c:out value="${medicineBySeries.medicineDose}" /><fmt:message bundle="${loc}" key="local.mg" /></p>
        </article>
        <article class="day-forecast">
            <h2> <fmt:message bundle="${loc}" key="local.packagePrice" /></h2>
            <p><c:out value="${medicineBySeries.packagePrice}" /><fmt:message bundle="${loc}" key="local.rub" /></p>
        </article>
        <article class="day-forecast">
            <h2><fmt:message bundle="${loc}" key="local.expirationDate" /> </h2>
            <p><c:out value="${medicineBySeries.medicineExpirationDate}" /></p>
        </article>
        <article class="day-forecast">
                <h2><fmt:message bundle="${loc}" key="local.productBalance" /></h2>
                <p><c:out value="${medicineBySeries.productBalance}" /></p>
            </article>
            <article class="day-forecast">
                <h2><fmt:message bundle="${loc}" key="local.prescriptionRequired" /></h2>
                <p><c:out value="${medicineBySeries.prescriptionRequired}" /></p>
            </article>
            <article class="day-forecast">
                <h2><fmt:message bundle="${loc}" key="local.producer" /></h2>
                <p><c:out value="${medicineBySeries.producer.producerFactoryName}" />,
                           <c:out value="${medicineBySeries.producer.producerCountry}" /></p>
            </article>
            <article class="day-forecast">
                            <h2><fmt:message bundle="${loc}" key="local.diseaseGroup" /></h2>
                            <p><c:out value="${medicineBySeries.diseaseGroup}" /></p>
                        </article>
        <c:if test="${user.userRole.name() eq 'PHARMACIST'}">
               <article class="day-forecast">
                               <h2><fmt:message bundle="${loc}" key="local.serialNumber" /></h2>
                               <p><c:out value="${medicineBySeries.serialNumber}" /></p>
                           </article>
                           <article class="day-forecast">
                               <h2><fmt:message bundle="${loc}" key="local.invoiceNumber" /></h2>
                               <p><c:out value="${medicineBySeries.invoiceNumber}" /></p>
                           </article>
                           <article class="day-forecast">
                               <h2><fmt:message bundle="${loc}" key="local.arrivalDate" /></h2>
                               <p><c:out value="${medicineBySeries.arrivalDate}" /></p>
                           </article>
                           <article class="day-forecast">
                                           <h2><fmt:message bundle="${loc}" key="local.productArrival" /></h2>
                                           <p><c:out value="${medicineBySeries.productArrival}" /></p>
                                       </article>

               </c:if>
               <br>
                 <c:if test="${user.userRole.name() eq 'DOCTOR'}">
                 <article class="day-forecast">

                <h2><fmt:message bundle="${loc}" key="local.add.prescription" />:<a href="/pharmacy/controller?command=GO_TO_PRESCRIPTION_PAGE&serialNumber=${medicineBySeries.serialNumber}">
                <fmt:message bundle="${loc}" key="local.prescribe" /></a><h2>
                        </article>
                  </c:if>

                      <c:if test="${user.userRole.name() eq 'CUSTOMER'}">
                      <article class="day-forecast">
                                  <form action="/pharmacy/controller" method="post">
                                      <input type="hidden" name="command" value="ADD_ENTRY_TO_CART">
                                      <h3><fmt:message bundle="${loc}" key="local.amount.medicine" />:</h3>
                                      <input type="number" name="packageAmount" min="1" max="${medicineBySeries.productBalance}" value="1" />
                                      <input type="hidden" name="serialNumber" value="${medicineBySeries.serialNumber}">
                                        <br>
                                      <input type="submit" class="btn btn-dark" value="${add}" />
                                  </form>

                                </article>
                              </c:if>
</article>

        <br>









    </div>
    <common:footer />
</body>

</html>