<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <!-- Left side column. contains the logo and sidebar -->
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <div class="row">
                <div class="col-md-3">

                    <!-- Profile Image -->
                    <div class="box box-primary">
                        <div class="box-body box-profile">
                            <h3 class="profile-username text-center">${vehicle.getConstructeur()} ${vehicle.getModele()}</h3>
                            <p>${vehicle.getNb_places()} places</p>
                            <ul class="list-group list-group-unbordered">
                                <li class="list-group-item">
                                    <b>R&eacute;servation(s)</b> <a class="pull-right">${allReservations.size()}</a>
                                </li>
                                <li class="list-group-item">
                                    <b>Conducteur(s)</b> <a class="pull-right">${allClients.size()}</a>
                                </li>
                            </ul>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
                <div class="col-md-9">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#rents" data-toggle="tab">R&eacute;servation(s)</a></li>
                            <li><a href="#drivers" data-toggle="tab">Conducteur(s)</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="active tab-pane" id="rents">
                                <div class="box-body no-padding">
                                    <table class="table table-striped">
                                        <tr>
                                            <th style="width: 10px">#</th>
                                            <th>Client</th>
                                            <th>Date de d&eacute;but</th>
                                            <th>Date de fin</th>
                                        </tr>
                                        <c:forEach items="${allReservations}" var="reservation">
                                        <tr>
                                            <td>${reservation.getId()}</td>
                                            <td>[${reservation.getClient().getId()}] ${reservation.getClient().getNom()} ${reservation.getClient().getPrenom()}</td>
                                            <td>${reservation.getDebut()}</td>
                                            <td>${reservation.getFin()}</td>
                                        </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                            <div class="tab-pane" id="drivers">
                                <!-- /.box-header -->
                                <div class="box-body no-padding">
                                    <table class="table table-striped">
                                        <tr>
                                            <th style="width: 10px">#</th>
                                            <th>Nom</th>
                                            <th>Pr&eacute;nom</th>
                                            <th>Date de naissance</th>
                                            <th>Email</th>
                                        </tr>
                                        <c:forEach items="${allClients}" var="client">
                                        <tr>
                                            <td>${client.getId()}</td>
                                            <td>${client.getNom()}</td>
                                            <td>${client.getPrenom()}</td>
                                            <td>${client.getNaissance()}</td>
                                            <td>${client.getEmail()}</td>
                                        </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                        </div>
                        <!-- /.tab-content -->
                    </div>
                    <!-- /.nav-tabs-custom -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->

        </section>
        <!-- /.content -->
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>
