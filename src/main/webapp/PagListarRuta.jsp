<%@page import="modelo.Ruta"%>
<%@page import="java.util.ArrayList"%>
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
            ArrayList<Ruta> lista = (ArrayList<Ruta>) request.getAttribute("lista");
        %>
        <div class="container-fluid">
            <br />
            <div class="card separacion">
                <div class="card-body">
                    <h4 class="blockquote">Listado de Rutas</h4>
                    <hr>
                    <a href="PagNuevaRuta.jsp" class="btn btn-success" title="Nueva Ruta">
                        <i class="fa fa-plus-circle"></i> Nuevo
                    </a>

                    <div class="table-responsive mt-4" style="padding-top: 10px;">

                        <table class="table  table-striped table-bordered text-center" id="tabla">
                            <thead>
                                <tr>
                                    <th class="text-center">Imagen</th>
                                    <th  class="text-center">Codigo</th>
                                    <th  class="text-center">Nombre</th>
                                    <th  class="text-center">Acción</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (Ruta m : lista) {
                                %>
                                <tr>
                                    <td>   
                                        <img src="<%=m.getImagenRuta()%>" 
                                             onerror="src='img/sin_imagen.png'"
                                             style="width: 90px;height: 100px;"/>
                                    </td>
                                    <td><%=m.getCodRuta()%></td>
                                    <td><%=m.getNomRuta()%></td>
                                    <td>
                                        <a href="RutaControlador?accion=editar&id=<%=m.getCodRuta()%>"  class="btn btn-info" title="Editar">
                                            <i class="fa fa-edit" ></i>
                                        </a>
                                        <a  href="RutaControlador?accion=eliminar&id=<%=m.getCodRuta()%>"  class="btn btn-danger" title="Eliminar">
                                            <i class="fa fa-trash" ></i>
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
