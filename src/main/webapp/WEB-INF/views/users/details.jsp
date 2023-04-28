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
                            <h3 class="profile-username text-center">${client.getNom()} ${client.getPrenom()}</h3>
                            <p>N&eacute; le ${client.getNaissance()}</p>
                            <p>Email : ${client.getEmail()}</p>
                            <ul class="list-group list-group-unbordered">
                                <li class="list-group-item">
                                    <b>R&eacute;servation(s)</b> <a class="pull-right">${allReservations.size()}</a>
                                </li>
                                <li class="list-group-item">
                                    <b>Voiture(s)</b> <a class="pull-right">${allVehicles.size()}</a>
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
                            <li><a href="#cars" data-toggle="tab">Voitures</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="active tab-pane" id="rents">
                                <div class="box-body no-padding">
                                    <table class="table table-striped">
                                        <tr>
                                            <th style="width: 10px">ID</th>
                                            <th>Voiture</th>
                                            <th>Date de d&eacute;but</th>
                                            <th>Date de fin</th>
                                        </tr>
                                        <c:forEach items="${allReservations}" var="reservation">
                                        <tr>
                                            <td>${reservation.getId()}</td>
                                            <td>${reservation.getVehicle().getConstructeur()} ${reservation.getVehicle().getModele()}</td>
                                            <td>${reservation.getDebut()}</td>
                                            <td>${reservation.getFin()}</td>
                                        </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                            <div class="tab-pane" id="cars">
                                <!-- /.box-header -->
                                <div class="box-body no-padding">
                                    <table class="table table-striped">
                                        <tr>
                                            <th style="width: 10px">ID</th>
                                            <th>Constructeur</th>
                                            <th>Mod&egrave;le</th>
                                            <th>Nombre de places</th>
                                        </tr>
                                        <c:forEach items="${allVehicles}" var="vehicle">
                                        <tr>
                                            <td>${vehicle.getId()}.</td>
                                            <td>${vehicle.getConstructeur()}</td>
                                            <td>${vehicle.getModele()}</td>
                                            <td>${vehicle.getNb_places()}</td>
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
