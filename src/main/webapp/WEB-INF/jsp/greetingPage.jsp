<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<html>
<style>
   h1 {
       margin: 1em 0 0.5em 0;
       color: #343434;
       font-weight: normal;
       font-family: 'Ultra', sans-serif;
       font-size: 100px;
       line-height: 100px;
       text-transform: uppercase;
       text-shadow: 0 2px white, 0 3px #777;
   }
   h3 {
       margin: 1em 0 0.5em 0;
       color: #343434;
       font-size: 22px;
       line-height: 40px;
       font-weight: normal;
       text-transform: uppercase;
       font-family: 'Orienta', sans-serif;
       letter-spacing: 1px;
       font-style: normal;
   }

     body {
 background: url(img/medical-supplies-placed-on-a-blue.jpg);
      background-size:cover;
      margin: 0;
      }

  </style>
<common:head title="Welcome" />

<body>
    <common:header />
    <div class="container">

        <c:if test="${accessDenied}">
            <fmt:message bundle="${loc}" key="local.access.denied" />
        </c:if>

        <br />
        <h3 align="center">
           <fmt:message bundle="${loc}" key="local.greeting.add" />
        </h3>

        <h1 align="center">
            <fmt:message bundle="${loc}" key="local.greeting.main.page" />
        </h1>
<br>
        <p align="center">
        <font size="5" color="black"> <em><fmt:message bundle="${loc}" key="local.care" /></em>
        </p>

<h3 align="center">
           <fmt:message bundle="${loc}" key="local.advert" />
        </h3>
        <section id="gallery">
              <div class="container">
                <div class="row">
                <div class="col-lg-4 mb-4">
                <div class="card">
                  <img src="img/teraflu.jpg" alt="" class="card-img-top">
                  <div class="card-body">
                    <h2 class="card-title" align="center">Терафлю</h2>
                    <p class="card-text"><small>ПОР. Д/ПРИГ. Р-РА Д/ПРИЕМА ВНУТРЬ (ЛИМОН) (ПАК. №10) FAMAR ORLEANS-ФРАНЦИЯ</small></p>
                   <a href="http://127.0.0.1:8080/pharmacy/controller?command=SHOW_MEDICINE_DETAILS&serialNumber=17022021" class="btn btn-outline-primary btn-sm" align="center"><fmt:message bundle="${loc}" key="local.doctor.details" /></a>

                  </div>
                 </div>
                </div>
              <div class="col-lg-4 mb-4">
              <div class="card">
                  <img src="img/orvis.jpg" alt="" class="card-img-top">
                  <div class="card-body">
                    <h2 class="card-title" align="center">Орвис</h2>
                    <p class="card-text"><small>ЧАЙ ДЛЯ ИММУНИТЕТА БЫСТРОРАСТВОРИМЫЙ (В САШЕ ПО 3,0 Г №20) ЭВАЛАР-РОССИЯ</small></p>
                   <a href="http://127.0.0.1:8080/pharmacy/controller?command=SHOW_MEDICINE_DETAILS&serialNumber=111000" class="btn btn-outline-primary btn-sm"><fmt:message bundle="${loc}" key="local.doctor.details" /></a>

                  </div>
                  </div>
                </div>
                <div class="col-lg-4 mb-4">
                <div class="card">
                  <img src="img/rinocil.jpg" alt="" class="card-img-top">
                  <div class="card-body">
                    <h2 class="card-title" align="center">Риноцил</h2>
                    <p class="card-text"><small>СПРЕЙ НАЗАЛЬНЫЙ (2,5МГ/0,25МГ)/1МЛ ФЛ. 10 МЛ №1) РУБИКОН-БЕЛАРУСЬ</small></p>
                   <a href="http://127.0.0.1:8080/pharmacy/controller?command=SHOW_MEDICINE_DETAILS&serialNumber=1602" class="btn btn-outline-primary btn-sm" align="center"><fmt:message bundle="${loc}" key="local.doctor.details" /></a>

                  </div>
                 </div>
                </div>
              </div>
            </div>
            </section>



    </div>
    <common:footer />
</body>

</html>