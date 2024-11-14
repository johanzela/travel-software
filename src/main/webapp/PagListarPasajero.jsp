<%@page import="modelo.*"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pasajero</title>
        <%@include file="include/Estilos.jsp" %>
    </head>
    <body>
        <%@include file="include/Header.jsp" %>

        <%
            ArrayList<Pasajero> lista = (ArrayList<Pasajero>) request.getAttribute("lista");
            Viaje viaje = (Viaje) request.getSession().getAttribute("viaje");

        %>
        <div class="container-fluid">
            <br />
            <div class="card separacion">
                <div class="card-body">
                    <h4 class="blockquote">Listado de Pasajero</h4>

                    <div class="row">
                        <div class="col-sm-8">
                            <div class="form-group row">
                                <label  class="col-sm-2" style="text-align: right; font-weight: bold;">Ruta:</label>
                                <div class="col-sm-10">
                                    <label><%=viaje.getNomRuta()%></label>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label  class="col-sm-2" style="text-align: right; font-weight: bold;">Nro Viaje:</label>
                                <div class="col-sm-10">
                                    <label><%=viaje.getNroViaje()%></label>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-4" style="text-align: left;">
                            <%
                                if (!viaje.getImagenRuta().trim().equals("")) {
                            %>
                            <div class="form-group row">
                                <img src="<%=viaje.getImagenRuta()%>" 
                                     onerror="src='img/sin_imagen.png'"
                                     style="width: 300px;height: 150px;"/>
                            </div>
                            <%
                                }
                            %>
                        </div>
                    </div>
                    <hr>
                    <a href="PagNuevoPasajero.jsp" class="btn btn-success" title="Nuevo Pasajero">
                        <i class="fa fa-plus-circle"></i> Nuevo
                    </a>

                    <div class="table-responsive mt-4" style="padding-top: 10px;">

                        <table class="table  table-striped table-bordered text-center" id="tabla">
                            <thead>
                                <tr>
                                    <th class="text-center"># Boleto</th>
                                    <th  class="text-center">Pasajero</th>
                                    <th  class="text-center">Asiento</th>
                                    <th  class="text-center">Tipo</th>
                                    <th  class="text-center">Pago (S/)</th>
                                    <th  class="text-center">Acción</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (Pasajero m : lista) {
                                %>
                                <tr>
                                    <td><%=m.getNroBoleto()%></td>
                                    <td><%=m.getNombres()%></td>
                                    <td><%=m.getNroAsiento()%></td>
                                    <td><%=m.getTipo()%></td>
                                    <td><%=m.getPago()%></td>
                                    <td>
                                        <a  href="PasajeroControlador?accion=eliminar&id=<%=m.getNroBoleto()%>"  class="btn btn-danger" title="Eliminar">
                                            <i class="fa fa-trash" ></i>
                                        </a>   
                                    </td>
                                </tr>
                                <%
                                    }
                                    if (lista.size() == 0) {
                                        out.print("<tr><td colspan='6' class='text-center'>No hay registros</td></tr>");
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </div>


        <%@include file="include/Js.jsp" %>

    </body>
</html>
