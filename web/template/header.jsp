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
            <span class="logo-mini">SERVEL</span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b draggable="true">SERVEL</b></span>
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
                            <img src="/integracion.java/resources/dist/img/user2-160x160.jpg" class="user-image" alt="${user.login}">
                            <span class="hidden-xs">${user.name}</span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- User image -->
                            <li class="user-header">
                                <img src="/integracion.java/resources/dist/img/user2-160x160.jpg" class="img-circle" alt="${user.login}">
                                <p>
                                    ${user.name}
                                    <small>${user.login}</small>
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
                                    <a href="/integracion.java/perfil" class="btn btn-default btn-flat">Perfil</a>
                                </div>
                                <div class="pull-right">
                                    <a href="/integracion.java/salir" class="btn btn-default btn-flat">Salir</a>
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
                <li><a href="./perfil"><i class="fa fa-home"></i> Inicio</a></li>
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-edit"></i>
                        <span>Mantenedores</span>
                        <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <li>
                            <a href="./mantenedores/usuarios"><i class="fa fa-user"></i> Usuarios</a>
                        </li>
                        <li>
                            <a href="./mantenedores/procesos"><i class="fa fa-table"></i> Procesos Electorales</a>
                        </li>
                        <li>
                            <a href="./mantenedores/partidos"><i class="fa fa-columns"></i> Partidos Políticos</a>
                        </li>
                        <li>
                            <a href="./mantenedores/padron"><i class="fa fa-list"></i> Padrón Electoral</a>
                        </li>
                        <li>
                            <a href="./mantenedores/votantes"><i class="fa fa-hand-o-up"></i> Votantes</a>
                        </li>
                    </ul>
                </li>
                <li><a href="./configuracion"><i class="fa fa-cogs"></i> Configuración del Sistema</a></li>
            </ul>
            
        </section>
        <!-- /.sidebar -->
    </aside>