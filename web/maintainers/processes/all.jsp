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
            <h1>Mantenedor Usuarios</h1>
        </section>
        <!-- Main content -->
        <section class="content animated fadeInDown">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">Procesos Electorales</h3>
                            <button type="submit" class="btn btn-primary pull-right" id="newItem">Nuevo</button>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <table id="mantenedor" class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>Nombre</th>
                                        <th>Estado</th>
                                        <th>Opciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${list}" var="process">
                                        <tr>
                                            <td>${process.name}</td>
                                            <td><span class="label label-${process.status==1?"success":(process.status==2?"info":(process.status==3?"default":"danger"))}">${process.status==1?"Activo":(process.status==2?"En Curso":(process.status==3?"Terminado":"Inactivo"))}</span></td>
                                            <td>
                                                <c:if test="${process.status==1 || process.status==0}">
                                                    <a class="btn btn-primary btn-xs btnEditar" data-id="${process.id}" data-url="./procesos/update" data-original-title="Editar" data-toggle="tooltip"><i class="fa fa-pencil-square-o"></i></a>
                                                    <c:if test="${process.status==1}"><a class="btn btn-primary btn-xs btnEliminar" data-id="${process.id}" data-url="./procesos/update" data-original-title="Eliminar" data-toggle="tooltip"><i class="fa fa-times-circle"></i></a></c:if>
                                                </c:if>
                                                <c:if test="${process.status==2}">
                                                    <a class="btn btn-primary btn-xs" href="/integracion.java/configuracion/resultados/proceso?p=${process.id}" data-original-title="Resultado Votación" data-toggle="tooltip"><i class="fa fa-bar-chart-o"></i></a>
                                                    <a class="btn btn-primary btn-xs stopProcess" data-id="${process.id}" data-url="./configuracion/detener/proceso" data-original-title="Terminar Proceso" data-toggle="tooltip"><i class="fa fa-times-circle"></i></a>
                                                </c:if>
                                                <c:if test="${process.status==3}">
                                                    <a class="btn btn-primary btn-xs" href="/integracion.java/configuracion/resultados/proceso?p=${process.id}" data-original-title="Resultado Votación" data-toggle="tooltip"><i class="fa fa-bar-chart-o"></i></a>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                                <tfoot></tfoot>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->
        </section>
        <!-- /.content -->
        <div class="modal" id="new">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                        <h4 class="modal-title">Detalles Proceso</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal form" id="addForm">
                            <input class="form-control" name="id" id="id" type="hidden">
                            <div class="form-group">
                                <label for="name" class="col-sm-2 control-label">Nombre</label>
                                <div class="col-sm-10">
                                    <input class="form-control" name="name" id="name" placeholder="Nombre" type="text" required="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="status" class="col-sm-2 control-label">Estado</label>
                                <div class="col-sm-10">
                                    <select name="status" id="status" class="form-control" style="width: 100%;" required="required">
                                        <option value="1">Activo</option>
                                        <option value="0">Inactivo</option>
                                    </select>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Cerrar</button>
                        <button type="button" class="btn btn-primary" id="addNew" data-controller="procesos" data-url="./procesos">Guardar</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div>
    </jsp:body>
</t:Master>