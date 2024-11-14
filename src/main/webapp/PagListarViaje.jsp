<%@page import="modelo.*"%>
<%@page import="java.util.ArrayList"%>
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
            ArrayList<Viaje> lista = (ArrayList<Viaje>) request.getAttribute("lista");
        %>
        <div class="container-fluid">
            <br />
            <div class="card separacion">
                <div class="card-body">
                    <h4 class="blockquote">Listado de Viajes</h4>
                    <hr>
                    <a href="PagNuevoViaje.jsp" class="btn btn-success" title="Nuevo Viaje">
                        <i class="fa fa-plus-circle"></i> Nuevo
                    </a>

                    <div class="table-responsive mt-4" style="padding-top: 10px;">

                        <table class="table  table-striped table-bordered text-center" id="tabla">
                            <thead>
                                <tr>
                                    <th class="text-center"># Viaje</th>
                                    <th  class="text-center">Ruta</th>
                                    <th  class="text-center">Fecha</th>
                                    <th  class="text-center">Hora</th>
                                    <th  class="text-center">Costo (S/)</th>
                                    <th  class="text-center">Acción</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (Viaje m : lista) {
                                %>
                                <tr>
                                    <td><%=m.getNroViaje()%></td>
                                    <td><%=m.getNomRuta()%></td>
                                    <td><%=m.getFecha()%></td>
                                    <td><%=m.getHora()%></td>
                                    <td><%=m.getCosto()%></td>
                                    <td>
                                        <a href="ViajeControlador?accion=editar&id=<%=m.getNroViaje()%>"  class="btn btn-info" title="Editar">
                                            <i class="fa fa-edit" ></i>
                                        </a>
                                        <a  href="ViajeControlador?accion=eliminar&id=<%=m.getNroViaje()%>"  class="btn btn-danger" title="Eliminar">
                                            <i class="fa fa-trash" ></i>
                                        </a>   
                                        <a  href="ViajeControlador?accion=ver_pasajeros&id=<%=m.getNroViaje()%>"  class="btn btn-success" title="Añadir Pasajeros">
                                            <i class="fa fa-user-plus" ></i>
                                        </a>
                                    </td>
                                </tr>
                                <%
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
