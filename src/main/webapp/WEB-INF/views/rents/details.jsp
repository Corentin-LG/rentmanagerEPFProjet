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
                            <dl>
                                <dt>ID</dt>
                                <dd>${reservation.getId()}</dd>
                                <dt>Client</dt>
                                <dd>${reservation.getClient().getNom()} ${reservation.getClient().getPrenom()}</dd>
                                <dt>Véhicule</dt>
                                <dd>${reservation.getVehicle().getModele()} ${reservation.getVehicle().getConstructeur()}</dd>
                                <dt>Début</dt>
                                <dd>${reservation.getDebut()}</dd>
                                <dt>Fin</dt>
                                <dd>${reservation.getFin()}</dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>
