<%@ tag trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ attribute name="doctor" required="true" type="com.epam.training.epharmacy.entity.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="localization.local" var="loc"/>

<form class="form-horizontal" action = "/pharmacy/controller" method = "post">
   <input type="hidden" name="command" value="UPDATE_PROFILE">
   <div class="form-group">
        <label class="col-sm-2 control-label"><fmt:message bundle="${loc}" key="local.name.surname" /></label>
        <div class="col-sm-3">
            <input class="form-control" type="text" name="userName" value="${doctor.fullName}">
        </div>
   </div>
   <div class="form-group">
        <label class="col-sm-2 control-label"><fmt:message bundle="${loc}" key="local.email" /></label>
        <div class="col-sm-3">
            <input class="form-control" type="text" name="email" value="${doctor.email}">
        </div>
   </div>
   <div class="form-group">
        <label class="col-sm-2 control-label"><fmt:message bundle="${loc}" key="local.telephone" /></label>
        <div class="col-sm-3">
            <input class="form-control" type="text" name="telNumber" value="${doctor.telNumber}">
        </div>
   </div>
   <div class="form-group">
              <label class="col-sm-2 control-label"><fmt:message bundle="${loc}" key="local.workPlace.change" /></label>
              <div class="col-sm-3">
                  <input class="form-control" type="text" name="workPlace" value="${doctor.workPlace}">
              </div>
         </div>
   <div class="form-group">
           <label class="col-sm-2 control-label"><fmt:message bundle="${loc}" key="local.password" /></label>
           <div class="col-sm-3">
               <input class="form-control" type="password" minlength="5" maxlength="15" name="password" value="" placeholder="Enter new password">
           </div>
      </div>

<c:if test="${param['successUpdate']}">
      <fmt:message bundle="${loc}" key="local.update.successful" />
</c:if>
<br>
   <div class="form-group">
        <button type="submit" class="btn btn-dark"/><fmt:message bundle="${loc}" key="local.update" /></button>
   <div class="form-group">
</form>