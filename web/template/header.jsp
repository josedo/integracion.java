<%-- 
    Document   : header
    Created on : 06-05-2016, 02:36:54 AM
    Author     : Joe-Xidu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="wrapper">
    <header class="main-header">
        <!-- Logo -->
        <a href="./perfil" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini">MiConstru</span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b draggable="true">MiConstructora</b></span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <!-- Messages: style can be found in dropdown.less-->
                    <!-- Notifications: style can be found in dropdown.less -->
                    <!-- Tasks: style can be found in dropdown.less -->
                    <!-- User Account: style can be found in dropdown.less -->
                    <!-- Control Sidebar Toggle Button -->
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <img src="/testjava/resources/dist/img/user2-160x160.jpg" class="user-image" alt="${user.user_login}">
                            <span class="hidden-xs">${user.employee_name}</span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- User image -->
                            <li class="user-header">
                                <img src="/testjava/resources/dist/img/user2-160x160.jpg" class="img-circle" alt="${user.user_login}">
                                <p>
                                    ${user.employee_name}
                                    <small>${user.user_login}</small>
                                </p>
                            </li>
                            <!-- Menu Body -->
                            <li class="user-body">
                                <div class="row">
                                    <div class="col-xs-4 text-center">
                                        <a href="#">link 1</a>
                                    </div>
                                    <div class="col-xs-4 text-center">
                                        <a href="#">link 2</a>
                                    </div>
                                    <div class="col-xs-4 text-center">
                                        <a href="#">link 3</a>
                                    </div>
                                </div>
                                <!-- /.row -->
                            </li>
                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <div class="pull-left">
                                    <a href="/testjava/perfil" class="btn btn-default btn-flat">Perfil</a>
                                </div>
                                <div class="pull-right">
                                    <a href="/testjava/salir" class="btn btn-default btn-flat">Salir</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section style="height: auto;" class="sidebar">
            <!-- /.search form -->
            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu">
                <li class="header">Navegación</li>
                <c:forEach items="${pageList}" var="objPage">
                    <c:if test="${objPage.parent==0}">
                        <c:choose>
                            <c:when test="${objPage.path=='#'}">
                            <li class="treeview">
                                <a href="${objPage.path}">
                                    <i class="fa ${objPage.icon}"></i>
                                    <span>${objPage.name}</span>
                                    <i class="fa fa-angle-left pull-right"></i>
                                </a>
                                <ul class="treeview-menu">
                                    <c:forEach items="${pageList}" var="objPageChild">
                                        <li>
                                        <c:if test="${objPageChild.id!=0 && objPage.id == objPageChild.parent}">
                                            <c:choose>
                                                <c:when test="${objPageChild.path=='#'}">
                                                    <a href="${objPageChild.path}">
                                                        <i class="fa ${objPageChild.icon}"></i>
                                                        <span>${objPageChild.name}</span>
                                                        <i class="fa fa-angle-left pull-right"></i>
                                                    </a>
                                                    <ul class="treeview-menu">
                                                        <c:forEach items="${pageList}" var="objPageSubChild">
                                                            <c:if test="${objPageSubChild.id!=0 && objPageChild.id == objPageSubChild.parent}">
                                                                <li><a href="${objPageSubChild.path}"><i class="fa ${objPageSubChild.icon}"></i> ${objPageSubChild.name}</a></li>
                                                            </c:if>
                                                        </c:forEach>
                                                    </ul>
                                                 </c:when>
                                                <c:otherwise>
                                                    <a href="${objPageChild.path}"><i class="fa ${objPageChild.icon}"></i> ${objPageChild.name}</a>
                                                </c:otherwise>
                                            </c:choose>
                                        </li>
                                        </c:if>
                                    </c:forEach>
                                </ul>
                            </li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="${objPage.path}"><i class="fa ${objPage.icon}"></i> ${objPage.name}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </c:forEach>
            </ul>
            
        </section>
        <!-- /.sidebar -->
    </aside>