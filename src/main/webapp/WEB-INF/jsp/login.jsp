<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.enter" var="enter" />

<html>
<common:head title="Log in" />
 <style>
body { background: url(img/medical-supplies-placed-on-a-blue.jpg);
      background-size:cover;
      margin: 0;

      }
      .register p {
          text-align: center;
          font-size: 16px;
          margin: 0
      }

      p {
          color: #999999;
          font-size: 14px;
          line-height: 24px
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
                          <h4 class="card-title mt-3 text-center">Log in</h4>

                          <c:if test="${param['wrongUser']}">
                                      <fmt:message bundle="${loc}" key="local.incorrect.data" />
                                  </c:if>

                          <form>

                          <input type="hidden" name="command" value="Login" />
                            <div class="form-group input-group">
                              <div class="input-group-prepend">
                                <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                              </div>
                              <input name="login" class="form-control" placeholder="login" type="text" required>
                            </div>

                                  <div class="form-group input-group">
                                    <div class="input-group-prepend">
                                    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                                  </div>
                                  <input name="password" class="form-control" placeholder="password" type="password" minlength="5"
                                        maxlength="15" title="Enter 5-15 symbols" required>
                                </div>






                             <div class="row">

                              <button type="submit" class="btn btn-primary btn-block"> ${enter} </button>
                            </div>
                            </form>

                           <div class="register">
                          <p> Don’t have an account? <a href="/pharmacy/controller?command=GO_TO_REGISTRATION_PAGE">Sign Up</a></p>
                          </div>


                        </div>
                      </div>
                    </div>
                  </div>
                </div>

             </section>

    <common:footer />
</body>

</html>