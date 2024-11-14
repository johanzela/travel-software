<%@page import="modelo.Ruta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reporte</title>
        <%@include file="include/Estilos.jsp" %>
    </head>
    <body>
        <%@include file="include/Header.jsp" %>

        <div class="container-fluid mt-3">
            <h3 style="text-align: center;">REPORTES</h3>
            <div class="card" >

                <div class="card-body">
                    <iframe src="ReporteControlador?accion=reporte1" width="600" height="520"></iframe>
                    <iframe src="ReporteControlador?accion=reporte2" width="600" height="520"></iframe>

                    <hr />

                    <form target="zona" action="ReporteControlador">
                        <div class="row">
                            <div  class="col-sm-2">
                                <div class="mb-3">
                                    <label class="form-label">Año</label>
                                    <input type="number" class="form-control" name="annio" required="" value="2023">
                                </div>
                            </div>
                            <div  class="col-sm-2">
                                <div class="mb-3">
                                    <label class="form-label">Mes</label>
                                    <select name="mes" class="form-control form-select">
                                        <option value="1">Enero</option>
                                        <option value="2">Febrero</option>
                                        <option value="3">Marzo</option>
                                        <option value="4">Abril</option>
                                        <option value="5">Mayo</option>
                                        <option value="6">Junio</option>
                                        <option value="7">Julio</option>
                                        <option value="8">Agosto</option>
                                        <option value="9">Septiembre</option>
                                        <option value="10">Octubre</option>
                                        <option value="11">Noviembre</option>
                                        <option value="12">Diciembre</option>
                                    </select>
                                </div>

                            </div>
                            <div  class="col-sm-3 mt-2">
                                <br />
                                <input type="hidden" name="accion" value="reporte3">
                                <button type="submit" class="btn btn-primary">Consultar</button>
                            </div>
                        </div>
                    </form>
                    <iframe name="zona" width="1200" height="530"></iframe>

                </div>

            </div>
        </div>

        <%@include file="include/Js.jsp" %>

    </body>
</html>
