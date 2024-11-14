<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio de sesión</title>
        <%@include file="include/Estilos.jsp" %>
    </head>

    <body class="color_fondo">

        <div class="container d-flex flex-column " style='width:80%;' >
            <div class="row vh-100">
                <div class="col-sm-10 col-md-6 col-lg-6 mx-auto d-table h-100">
                    <div class="d-table-cell align-middle">
                        <div class="card">
                            <div class="card-body">
                                <div class="m-sm-4">
                                    <div class="text-center">
                                        <img src="img/logo.png"  class="img-fluid rounded-circle" width="222" height="222" />
                                    </div>
                                    <form method="post" action="AuthControlador">
                                        <%
                                            if (request.getAttribute("error") != null) {
                                                out.print("<div class='alert alert-danger' role='alert'>" + request.getAttribute("error") + "</div>");
                                            }
                                        %>
                                        <div class="mb-3">
                                            <label class="form-label">Correo:</label>
                                            <input  class="form-control form-control-lg" type="text" name="correo" required=""/>
                                        </div>
                                        <div class="mb-3">
                                            <label class="form-label">Contraseña:</label>
                                            <input  class="form-control form-control-lg" type="password" name="password" required=""/>
                                        </div>
                                        <div class="text-center mt-3">
                                            <div class="d-grid gap-2">
                                                <input type="hidden" name="accion" value="login">
                                                <button type="submit" class="btn btn-lg btn-primary">Iniciar Sesión</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <%@include file="include/Js.jsp" %>
    </body>
</html>
