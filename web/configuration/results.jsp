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
            <h1>Resultados Votación ${process.name}</h1>
        </section>
        <!-- Main content -->
        <section class="content animated fadeInDown">
            <div class="row">
                <c:forEach items="${candidatesProcess}" var="candidateProcess">
                    <div class="col-lg-3 col-xs-6">
                        <div class="small-box bg-yellow">
                            <c:forEach items="${candidates}" var="candidate">
                                <c:if test="${candidateProcess.id_candidate == candidate.id}">
                                    <div class="inner">
                                        <h3>${candidateProcess.votes == 0 ? 0 : df.format(candidateProcess.votes/total*100)}%</h3>
                                        <p>${candidate.name}</p>
                                    </div>
                                    <div class="icon">
                                        <i class="ion ion-person"></i>
                                    </div>
                                    <c:forEach items="${parties}" var="part">
                                        <c:if test="${candidate.id_part == part.id}">
                                            <a href="#" class="small-box-footer">${part.name}</a>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </c:forEach>
                <div class="col-md-12">
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <i class="fa fa-bar-chart-o"></i>
                            <h3 class="box-title">Gráfico Comparativo</h3>
                            <div class="box-tools pull-right">
                                <button data-toggle="tooltip" data-original-title="Minimizar"  type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                </button>
                                <button data-toggle="tooltip" data-original-title="Actualizar registro de votos" data-url="./proceso" data-id="${process.id}" type="button" class="btn btn-box-tool refreshResults"><i class="fa fa-refresh"></i></button>
                            </div>
                        </div>
                        <div class="box-body" style="">
                            <div class="chart"><canvas id="barChart"></canvas>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.row -->
        </section>
        <script>
            var areaChartData = {
            labels  : [
                <c:forEach items="${candidatesProcess}" var="candidateProcess">
                    <c:forEach items="${candidates}" var="candidate">
                        <c:if test="${candidateProcess.id_candidate == candidate.id}">
                            "${candidate.name}",
                        </c:if>
                    </c:forEach>
                </c:forEach>
           ],
            datasets: [
              {
                label               : 'Electronics',
                fillColor           : '#00a65a',
                strokeColor         : '#00a65a',
                pointColor          : '#00a65a',
                pointStrokeColor    : '#c1c7d1',
                pointHighlightFill  : '#fff',
                pointHighlightStroke: 'rgba(220,220,220,1)',
                data                : [
                    <c:forEach items="${candidatesProcess}" var="candidateProcess">
                        ${candidateProcess.votes},
                    </c:forEach>
                ]
              }
            ]
        }
        </script>
        <!-- /.content -->
    </jsp:body>
</t:Master>