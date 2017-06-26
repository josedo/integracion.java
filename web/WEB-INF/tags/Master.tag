<%-- 
    Document   : Master
    Created on : 09-dic-2015, 12:41:50
    Author     : Joe
--%>

<%@tag description="Pagina Maestra" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${sessionScope.user== null}">
   <c:redirect url="/login"/>
</c:if>
<%@attribute name="header" fragment="true" %>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<jsp:include page="/template/head.jsp" />
    <body class="page-header-fixed page-quick-sidebar-over-content skin-blue">
        <jsp:include page="/template/header.jsp" />
        <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper">
            <jsp:doBody/>
        </div>
        <jsp:include page="/template/footer.jsp" />

    </body>

</html>
