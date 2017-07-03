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
                            <h3 class="box-title">Padrón Electoral</h3>
                            <button type="submit" class="btn btn-primary pull-right" id="newItem">Nuevo</button>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <table id="mantenedor" class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>Mesa</th>
                                        <th>Sala</th>
                                        <th>Local</th>
                                        <th>Comuna</th>
                                        <th>Estado</th>
                                        <th>Opciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${list}" var="census">
                                        <tr>
                                            <td>${census.table}</td>
                                            <td>${census.room}</td>
                                            <td>${census.local}</td>
                                            <td>${census.district}</td>
                                            <td><span class="label label-${census.status==1?"success":"danger"}">${census.status==1?"Activo":"Inactivo"}</span></td>
                                            <td>
                                                <a class="btn btn-primary btn-xs btnEditar" data-id="${census.id}" data-url="./padron/update" data-original-title="Editar" data-toggle="tooltip"><i class="fa fa-pencil-square-o"></i></a>
                                                <c:if test="${census.status==1}"><a class="btn btn-primary btn-xs btnEliminar" data-id="${census.id}" data-url="./padron/update" data-original-title="Eliminar" data-toggle="tooltip"><i class="fa fa-times-circle"></i></a></c:if>
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
                        <h4 class="modal-title">Detalles Padrón Electoral</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal form" id="addForm">
                            <input class="form-control" name="id" id="id" type="hidden">
                            <div class="form-group">
                                <label for="table" class="col-sm-2 control-label">Mesa</label>
                                <div class="col-sm-10">
                                    <input class="form-control" name="table" id="table" placeholder="Mesa" type="text" required="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="room" class="col-sm-2 control-label">Sala</label>
                                <div class="col-sm-10">
                                    <input class="form-control" name="room" id="room" placeholder="Sala" type="text" required="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="local" class="col-sm-2 control-label">Local</label>
                                <div class="col-sm-10">
                                    <input class="form-control" name="local" id="local" placeholder="Local" type="text" required="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="region" class="col-sm-2 control-label">Region</label>
                                <div class="col-sm-10">
                                    <select name="region" id="region" class="form-control" style="width: 100%;" required="required">
                                        <c:forEach items="${regions}" var="region">
                                            <option value="${region.key}">${region.value}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="province" class="col-sm-2 control-label">Provincia</label>
                                <div class="col-sm-10">
                                    <select name="province" id="province" class="form-control" style="width: 100%;" required="required">
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="district" class="col-sm-2 control-label">Comuna</label>
                                <div class="col-sm-10">
                                    <select name="district" id="district" class="form-control" style="width: 100%;" required="required">
                                    </select>
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
                        <button type="button" class="btn btn-primary" id="addNew" data-controller="usuarios" data-url="./padron">Guardar</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div>
    </jsp:body>
</t:Master>