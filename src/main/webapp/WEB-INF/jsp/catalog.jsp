<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@ taglib prefix="medicine" tagdir="/WEB-INF/tags/medicine" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<html>
 <style>
body { background: url(img/medical-supplies-placed-on-a-blue.jpg);
      background-size:cover;
      margin: 0;

      }
       </style>
<common:head title="Catalog" />

<body>
    <div class="container">
        <common:header />
        <h2>
            <fmt:message bundle="${loc}" key="local.sold.now" />
        </h2>
        <div class="row">
            <h2 class="col-md-8 offset-md-1">
                <fmt:message bundle="${loc}" key="local.catalog.name" />
            </h2>
        </div>
        <div class="row">
            <div class="col-md-8 offset-md-1">
                <form class="form-inline" action="/pharmacy/controller" method="post">
                    <input type="hidden" name="command" value="GO_TO_CATALOG">
                    <input class="form-control col-md-4 mr-sm-3 mb-2" type="text" name="commercialName" value="" placeholder="Type medicine name"/>
                    <button class="btn btn-dark mb-2" type="submit">Search</button>
                </form>
            </div>
        </div>

        <medicine:medicinesTable medicineList="${medicines}" />



        <c:if test="${not empty analogs}">
           <br><br><br>
           <div class="row justify-content-center">
                <div>
                    <h3><fmt:message bundle="${loc}" key="local.analogs" /></h3> <br>
                </div>
           </div>

           <medicine:medicinesTable medicineList="${analogs}" />

        </c:if>
        <common:footer />
    </div>
</body>

</html>