<%-- 
    Document   : mantenedorCondominios
    Created on : 07-05-2016, 08:31:20 AM
    Author     : Joe-Xidu
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:Master>
    <jsp:body>
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>Perfil</h1>
        </section>
        <!-- Main content -->
        <section class="content animated fadeInDown">
        <div class="row">
            <div class="col-xs-3">
            	<div class="box box-primary">
                    <div class="box-body box-profile">
                        <img src="resources/dist/img/user2-160x160.jpg" class="profile-user-img img-responsive img-circle" alt="Usuario">
                        <h3 class="profile-username text-center">${user.name}</h3>
                        <p class="text-muted text-center">${user.profile}</p>
                        <ul class="list-group list-group-unbordered">
                            <li class="list-group-item">
                                <b>Rut</b>
                                <a class="pull-right">${user.rut}-${user.dv}</a>
                            </li>
                            <li class="list-group-item">
                                <b>Correo</b><br>
                                <a class="email-large">${user.login}</a>
                            </li>
                        </ul>
                        <button id="change-password" class="btn btn-primary btn-block"><b>Cambiar Contraseña</b></button>
                    </div>
                    <!-- /.box-body -->
                </div>
            </div>
        </div>
    </section>
        <!-- /.content -->
    </jsp:body>
</t:Master>