<%-- 
    Document   : login
    Created on : 07-05-2016, 08:08:05 AM
    Author     : Joe-Xidu
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="include/head.jsp" />
<c:if test="${sessionScope.user!= null}">
   <c:redirect url="./perfil"/>
</c:if>
    <body class="hold-transition login-page">
        <div class="login-box">
            <div class="login-logo">
                <a href="index.htm"></a>
            </div>
            <!-- /.login-logo -->
            <div class="login-box-body">
                <p class="login-box-msg">Ingresar a SERVEL</p>
                <form class="form" action="./login" method="POST">
                    <div class="form-group has-feedback">
                        <input type="text" class="form-control" placeholder="Ingrese usuario" id="login" name="login" required="required">
                        <span class="glyphicon glyphicon-user form-control-feedback"></span>
                    </div>
                    <div class="form-group has-feedback">
                        <input type="password" class="form-control" placeholder="Ingrese contraseña" id="password" name="password" required="required">
                        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                    </div>
                    <div class="row">
                        <!-- /.col -->
                        <div class="col-xs-4 pull-right">
                            <button type="submit" class="btn btn-primary btn-block btn-flat" id="login">Ingresar</button>
                        </div>
                        <!-- /.col -->
                    </div>
                </form>
                <div class="social-auth-links text-center"></div>
                <!-- /.social-auth-links -->
                <a href="#">Recuperar contraseña</a>
            </div>
            <!-- /.login-box-body -->
        </div>
        <!-- /.register-box -->
        <!-- jQuery 2.1.4 -->
        <script src="resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
        <!-- Bootstrap 3.3.5 -->
        <script src="resources/bootstrap/js/bootstrap.min.js"></script>
        <script src="resources/plugins/pace/pace.js"></script>
        <!-- iCheck -->
        <script src="resources/plugins/iCheck/icheck.min.js"></script>
        <!-- SweetAlert -->
        <script src="resources/plugins/sweetalert/sweetalert.min.js"></script>
        <!-- page script -->
        <script src="resources/dist/js/scripts.js"></script>


    </body>
</html>
