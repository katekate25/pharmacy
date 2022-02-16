<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/order" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>

<html>
<common:head title="News" />
<style>
body { background: url(img/medical-supplies-placed-on-a-blue.jpg);
      background-size:cover;
      margin: 0;

      }
       </style>
<body>
    <common:header />
    <div class="container">
     <br><br><br>
 <section id="gallery">
              <div class="container">
                <div class="row">
                <div class="col-lg-4 mb-4">
                <div class="card">
                  <img src="img/mh.jpg" alt="" class="card-img-top">
                  <div class="card-body">
                    <h2 class="card-title" align="center"><fmt:message bundle="${loc}" key="local.ministry" /></h2>
                    <p class="card-text" align="center"><fmt:message bundle="${loc}" key="local.actual" /></p>
                   <a href="http://minzdrav.gov.by/ru/" class="btn btn-outline-primary btn-sm" align="center"><fmt:message bundle="${loc}" key="local.doctor.details" /></a>

                  </div>
                 </div>
                </div>
              <div class="col-lg-4 mb-4">
              <div class="card">
                  <img src="img/covid.jpg" alt="" class="card-img-top">
                  <div class="card-body">
                    <h2 class="card-title" align="center"><fmt:message bundle="${loc}" key="local.coronavirus.news" /></h2>

                   <a href="https://www.who.int/ru/emergencies/diseases/novel-coronavirus-2019/advice-for-public" class="btn btn-outline-primary btn-sm"><fmt:message bundle="${loc}" key="local.doctor.details" /></a>

                  </div>
                  </div>
                </div>
                <div class="col-lg-4 mb-4">
                <div class="card">
                  <img src="img/teenager.jpg" alt="" class="card-img-top">
                  <div class="card-body">
                    <h2 class="card-title" align="center"><fmt:message bundle="${loc}" key="local.teenagers.health" /></h2>

                   <a href="https://teenage.by/articles/zdorove" class="btn btn-outline-primary btn-sm" align="center"><small><fmt:message bundle="${loc}" key="local.doctor.details" /></small></a>

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