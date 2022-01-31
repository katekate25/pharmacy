<%@ tag trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ attribute name="customer" required="true" type="com.epam.training.epharmacy.entity.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>

<form class="form-horizontal" action = "/pharmacy/controller" method = "post">
   <input type="hidden" name="command" value="UPDATE_PROFILE">
   <div class="form-group">
        <label class="col-sm-2 control-label"><fmt:message bundle="${loc}" key="local.name.surname" /></label>
        <div class="col-sm-3">
            <input class="form-control" type="text" name="userName" value="${customer.fullName}">
        </div>
   </div>
   <div class="form-group">
        <label class="col-sm-2 control-label"><fmt:message bundle="${loc}" key="local.email" /></label>
        <div class="col-sm-3">
            <input class="form-control" type="text" name="email" value="${customer.email}">
        </div>
   </div>
   <div class="form-group">
        <label class="col-sm-2 control-label"><fmt:message bundle="${loc}" key="local.telephone" /></label>
        <div class="col-sm-3">
            <input class="form-control" type="text" name="telNumber" value="${customer.telNumber}">
        </div>
   </div>
   <div class="form-group">
           <label class="col-sm-2 control-label"><fmt:message bundle="${loc}" key="local.password" /></label>
           <div class="col-sm-3">
               <input class="form-control" type="text" name="password" value="" placeholder="Enter new password">
           </div>
      </div>
   <div class="form-group">
        <button type="submit" class="btn btn-dark"/>Update</button>
   <div class="form-group">
</form>