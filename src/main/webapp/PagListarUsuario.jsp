<%@page import="modelo.Usuario"%>
<%@page import="modelo.Ruta"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuarios</title>
        <%@include file="include/Estilos.jsp" %>
    </head>
    <body>
        <%@include file="include/Header.jsp" %>

        <%
            ArrayList<Usuario> lista = (ArrayList<Usuario>) request.getAttribute("lista");
        %>
        <div class="container-fluid">
            <br />
            <div class="card separacion">
                <div class="card-body">
                    <h4 class="blockquote">Listado de Usuarios</h4>
                    <hr>
                    <a href="PagNuevoUsuario.jsp" class="btn btn-success" title="Nueva Ruta">
                        <i class="fa fa-plus-circle"></i> Nuevo
                    </a>

                    <div class="table-responsive mt-4" style="padding-top: 10px;">

                        <table class="table  table-striped table-bordered text-center" id="tabla">
                            <thead>
                                <tr>
                                    <th class="text-center">Codigo</th>
                                    <th  class="text-center">Nombre</th>
                                    <th  class="text-center">Apellido</th>
                                    <th  class="text-center">Correo</th>
                                    <th  class="text-center">Contraseña</th>
                                    <th  class="text-center">Acción</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (Usuario m : lista) {
                                %>
                                <tr>
                                    <td><%=m.getCodUsuario()%></td>
                                    <td><%=m.getNombres()%></td>
                                    <td><%=m.getApellidos()%></td>
                                    <td><%=m.getCorreo()%></td>
                                    <td><%=m.getPassword()%></td>
                                    <td>
                                        <a href="UsuarioControlador?accion=editar&id=<%=m.getCodUsuario()%>"  class="btn btn-info" title="Editar">
                                            <i class="fa fa-edit" ></i>
                                        </a>
                                        <a  href="UsuarioControlador?accion=eliminar&id=<%=m.getCodUsuario()%>"  class="btn btn-danger" title="Eliminar">
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
