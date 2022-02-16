<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/order" %>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>
<html>
<common:head title="Error" />
<style>
body { background: url(img/medical-supplies-placed-on-a-blue.jpg);
      background-size:cover;
      margin: 0;

      }
       </style>
<body>
    <common:header />


<br><br><br>

 <div class="card" style="width: 18rem; margin:auto">
   <div class="card-body">
     <h5 class="card-title" align="center"><fmt:message bundle="${loc}" key="local.oops" /></h5>
      <h6 class="card-subtitle mb-2 text-muted" align="center"><fmt:message bundle="${loc}" key="local.error" /></h6>
   </div>
 </div>
    <common:footer />
</body>

</html>