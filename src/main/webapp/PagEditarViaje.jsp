<%@page import="modelo.Ruta"%>
<%@page import="dao.RutaDAO"%>
<%@page import="dao.ViajeDAO"%>
<%@page import="modelo.Viaje"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Viaje</title>
        <%@include file="include/Estilos.jsp" %>
    </head>
    <body>
        <%@include file="include/Header.jsp" %>
        <%
            Viaje item = (Viaje) request.getAttribute("item");
        %>
        <div class="container mt-3">
            <div class="card" style="width: 80%;margin: auto;">
                <div class="card-header">
                    <h5>Editar Viaje</h5>
                </div>
                <div class="card-body">
                    <form action="ViajeControlador" method="post">

                        <div class="row g-3">
                            <div class="col-md-6">
                                <label class="form-label">Ruta:</label>
                                <select class="form-control" name="ruta" required="">
                                    <option value="">::: Seleccione :::</option>
                                    <%
                                        for (Ruta v : new RutaDAO().ListarTodos()) {
                                            if (v.getCodRuta().equalsIgnoreCase(item.getCodRuta())) {
                                                out.print("<option value='" + v.getCodRuta() + "' selected>" + v.getNomRuta() + "</option>");
                                            } else {
                                                out.print("<option value='" + v.getCodRuta() + "'>" + v.getNomRuta() + "</option>");
                                            }
                                        }
                                    %>
                                </select>
                            </div>

                            <div class="col-md-6">
                                <label class="form-label">Costo (S/)</label>
                                <input type="number" step="0.1" class="form-control"   required="" name="costo" value="<%=item.getCosto()%>">
                            </div>
                        </div>

                        <div class="row g-3">
                            <div class="col-md-6">
                                <label class="form-label">Fecha:</label>
                                <input type="date"  class="form-control"   required="" name="fecha" value="<%=item.getFecha()%>">
                            </div>

                            <div class="col-md-6">
                                <label class="form-label">Hora:</label>
                                <input type="time" class="form-control"   required="" name="hora" value="<%=item.getHora()%>" >
                            </div>
                        </div>

                        <div class="mb-3">
                            <br>
                            <input type="hidden" name="id" value="<%=item.getNroViaje()%>">
                            <input type="hidden" name="accion" id="accion" value="edicion" >
                            <button class="btn btn-primary">
                                <i class="fa fa-save"></i>&nbsp; Guardar
                            </button>
                        </div>
                    </form>

                </div>
            </div>
        </div>

        <%@include file="include/Js.jsp" %>

    </body>
</html>
