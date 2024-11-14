<%@page import="modelo.Ruta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rutas</title>
        <%@include file="include/Estilos.jsp" %>
    </head>
    <body>
        <%@include file="include/Header.jsp" %>

        <%
            Ruta item = (Ruta) request.getAttribute("item");
        %>
        <div class="container mt-3">
            <div class="card" style="width: 80%;margin: auto;">
                <div class="card-header">
                    <h5>Editar Ruta</h5>
                </div>
                <div class="card-body">
                    <form action="RutaControlador" method="post">
                        <div class="row g-3">
                            <div class="col-md-6">
                                <label class="form-label">Nombre:</label>
                                <input type="text" class="form-control"  maxlength="30" required="" name="nombre" value="<%=item.getNomRuta()%>">
                            </div>

                            <div class="col-md-6">
                                <label class="form-label">Imagen Url:</label>
                                <input type="text" class="form-control"  maxlength="10000" required="" name="imagen" value="<%=item.getImagenRuta()%>">
                            </div>
                        </div>

                        <div class="mb-3">
                            <br>
                            <input type="hidden" name="id" value="<%=item.getCodRuta()%>">
                            <input type="hidden" name="accion" id="accion" value="edicion" >
                            <button class="btn btn-primary">
                                <i class="fa fa-save"></i>&nbsp; Editar
                            </button>
                        </div>
                    </form>

                </div>
            </div>
        </div>

        <%@include file="include/Js.jsp" %>

    </body>
</html>
