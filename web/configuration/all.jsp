<%-- 
    Document   : listado
    Created on : 02-04-2017, 03:37:02 PM
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
            <h1>Configuración Procesos Electorales</h1>
        </section>
        <!-- Main content -->
        <section class="content animated fadeInDown">
            <div class="row">
                <c:forEach items="${processes}" var="process">
                    <div class="col-xs-5">
                        <div class="box box-default direct-chat direct-chat-success">
                            <div class="box-header with-border">
                                <h3 class="box-title">${process.name}</h3>
                                <div class="box-tools pull-right">
                                    <c:if test="${process.status == 1}">
                                        <span data-toggle="tooltip" title="" class="badge bg-green" data-original-title="Candidatos Disponibles">${candidatesActives.size()}</span>
                                        <button type="button" class="btn btn-default btn-sm" data-toggle="tooltip" title="" data-widget="chat-pane-toggle" data-original-title="Candidatos">
                                            <i class="fa fa-user"></i>
                                        </button>
                                    </c:if>
                                    <c:if test="${process.status == 2}">
                                        <span class="badge bg-green">En Curso</span>
                                    </c:if>
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-box-tool btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                                            <i class="fa fa-bars"></i>
                                        </button>
                                        <ul class="dropdown-menu pull-right" role="menu">
                                            <li><a class="clearProcess" href="#" data-url="./configuracion/candidatos/borrar" data-id="${process.id}">Vaciar Candidatos</a></li>
                                            <c:if test="${process.status == 1}">
                                                <li><a class="startProcess" href="#" data-url="./configuracion/comenzar/proceso" data-id="${process.id}">Comenzar Proceso de Votación</a></li>
                                            </c:if>
                                            <c:if test="${process.status == 2}">
                                                <li><a class="stopProcess" href="#" data-url="./configuracion/detener/proceso" data-id="${process.id}">Detener Proceso de Votación</a></li>
                                            </c:if>
                                            <li class="divider"></li>
                                            <li><a  href="./configuracion/resultados/proceso?p=${process.id}">Detalle Votación</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="box-body">
                                <ul class="users-list clearfix" style="min-height: 300px">
                                    <c:forEach items="${candidatesProcess}" var="candidateProcess">
                                        <c:if test="${candidateProcess.id_process == process.id}">
                                            <c:forEach items="${candidates}" var="candidate">
                                                <c:if test="${candidateProcess.id_candidate == candidate.id}">
                                                    <li>
                                                        <img src="/integracion.java/resources/dist/img/user1-128x128.jpg" alt="Candidato">
                                                        <a class="users-list-name" href="#">${candidate.name}</a>
                                                        <c:forEach items="${parties}" var="part">
                                                            <c:if test="${candidate.id_part == part.id}">
                                                                <span class="users-list-date">${part.name}</span>
                                                            </c:if>
                                                        </c:forEach> 
                                                        <c:if test="${process.status < 2}">
                                                            <a class="text-red btnEliminar" href="#" data-url="./configuracion/candidato/borrar" data-id="${candidate.id}">Eliminar</a>
                                                        </c:if>
                                                    </li>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                    </c:forEach>
                                </ul>
                                <div class="direct-chat-contacts">
                                    <ul class="contacts-list">
                                        <c:forEach items="${candidatesActives}" var="candidate">
                                            <li>
                                                <a href="#" data-process="${process.id}" data-candidate="${candidate.id}" data-url="./configuracion" class="addCandidate">
                                                    <img class="contacts-list-img" src="/integracion.java/resources/dist/img/user1-128x128.jpg" alt="Candidato">
                                                    <div class="contacts-list-info">
                                                        <span class="contacts-list-name">
                                                            ${candidate.name}
                                                            <small class="contacts-list-date pull-right">Click para agregar</small>
                                                        </span>
                                                        <c:forEach items="${parties}" var="part">
                                                            <c:if test="${candidate.id_part == part.id}">
                                                                <span class="contacts-list-msg">${part.name}</span>
                                                            </c:if>
                                                        </c:forEach>
                                                    </div>
                                                </a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <!-- /.row -->
        </section>
        <!-- /.content -->
    </jsp:body>
</t:Master>