<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/common" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.register" var="register" />

<html>
<common:head title="Registration" />
 <style>
body { background: url(img/medical-supplies-placed-on-a-blue.jpg);
      background-size:cover;
      margin: 0;

      }
      .login p {
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
                          <h4 class="card-title mt-3 text-center">Create Account</h4>
                          <p class="text-center">Get started with your free account</p>
                           <c:if test='${param["userExists"]}'>
                                         <p style="color:red">
                                             <fmt:message bundle="${loc}" key="local.user.already.exist" />
                                         </p>
                                     </c:if>

                          <form>

                          <input type="hidden" name="command" value="Registration" />
                            <div class="form-group input-group">
                              <div class="input-group-prepend">
                                <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                              </div>
                              <input name="name" class="form-control" placeholder="Full name" type="text" required>
                            </div>
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
                                <div class="form-group input-group">
                                  <div class="input-group-prepend">
                                  <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                                </div>
                                <input type="tel" name="telNumber"  class="form-control" placeholder="telNumber"
                                       pattern="[0-9]{2} [0-9]{3} [0-9]{2} [0-9]{2}"
                                       required>


                              </div><small>Format: 12 345 67 89</small>
                              <div class="form-group input-group">
                                <div class="input-group-prepend">

                                <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
                              </div>
                              <input name="email" class="form-control" placeholder="Email address" type="email">
                            </div>
                            <div class="form-group input-group">
                              <div class="input-group-prepend">

                              <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                                </div>
                                <input name="age" class="form-control" placeholder="age" type="text" required>
                              </div>



                                <c:if test="${user.userRole.name() eq 'PHARMACIST'}">
                                <div class="form-group input-group">
                                <div class="input-group-prepend">

                                <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                                </div>
                                <input name="workPlace" class="form-control" placeholder="workPlace" type="text" required>
                              </div>

                             <div class="form-group input-group">
                              <div class="input-group-prepend">
                              <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                                    </div>

                         <select name="specialization" placeholder="specialization" required>
                            <option value="Гастроэнтеролог">Гастроэнтеролог</option>
                            <option value="Гинеколог">Гинеколог</option>
                            <option value="Дерматолог">Дерматолог</option>
                            <option value="Кардиолог">Кардиолог</option>
                            <option value="ЛОР">ЛОР</option>
                            <option value="Невролог">Невролог</option>
                            <option value="Офтальмолог">Офтальмолог</option>
                            <option value="Педиатр">Педиатр</option>
                            <option value="Психотерапевт">Психотерапевт</option>
                            <option value="Терапевт">Терапевт</option>
                            <option value="Хирург">Хирург</option>
                            <option value="Эндокринолог">Эндокринолог</option>
                        </select>
                      </div>
                     </c:if>

                           <div class="row">
                              <button type="submit" class="btn btn-primary btn-block">  ${register} </button>
                            </div>
                            </form>

                           <div class="login">
                          <p> Have an account? <a href="/pharmacy/controller?command=GO_TO_LOGIN_PAGE">Log In</a></p>
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