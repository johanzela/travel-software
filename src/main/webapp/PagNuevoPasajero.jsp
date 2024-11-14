<%@page import="utils.Utileria"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Pasajero"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Ruta"%>
<%@page import="dao.RutaDAO"%>
<%@page import="dao.ViajeDAO"%>
<%@page import="modelo.Viaje"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pasajero</title>
        <%@include file="include/Estilos.jsp" %>
    </head>
    <body onload="fnCalcularPago()">
        <%@include file="include/Header.jsp" %>
        <%
            Viaje viaje = (Viaje) request.getSession().getAttribute("viaje");
        %>
        <div class="container mt-3">
            <a href="PasajeroControlador?accion=listar">Volver atrás</a>
            <div class="card" style="width: 80%;margin: auto;">
                <div class="card-header">
                    <h5>Nuevo Pasajero</h5>
                </div>
                <div class="card-body">
                    <form action="PasajeroControlador" method="post">
                        <div class="row">
                            <div class="col-sm-8">
                                <div class="form-group row">
                                    <label  class="col-sm-4" style="text-align: right; font-weight: bold;">Ruta:</label>
                                    <div class="col-sm-8">
                                        <label><%=viaje.getNomRuta()%></label>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label  class="col-sm-4" style="text-align: right; font-weight: bold;">Nro Viaje:</label>
                                    <div class="col-sm-8">
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
                                         onerror="src='img/sin_imagen.png'"class="img-fluid"
                                         style="width: 200px;height: 120px;"/>
                                </div>
                                <%
                                    }
                                %>
                            </div>
                        </div>
                        <hr />
                        <div class="row g-3">
                            <div class="col-md-6">
                                <label class="form-label">Nombres:</label>
                                <input type="text"  class="form-control"   required="" name="nombres" maxlength="70" >
                            </div>

                            <div class="col-md-3">
                                <label class="form-label">Nro Asiento:</label>
                                <select class="form-control" name="asiento" required="">
                                    <option value="">::: Seleccione :::</option>
                                    <%
                                        ArrayList<Pasajero> lista = (ArrayList<Pasajero>) request.getSession().getAttribute("pasajeros");
                                        List<Integer> asientos = Utileria.CargarAsientosDisp(lista);
                                        for (Integer i : asientos) {
                                            out.print("<option value='" + i + "'>" + i + "</option>");
                                        }
                                    %>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <label class="form-label">Tipo:</label>
                                <select class="form-control" name="tipo" id="tipo" required="" onchange="fnCalcularPago()">
                                    <option value="">::: Seleccione :::</option>
                                    <option value="Turista">Turista</option>
                                    <option value="Ejecutivo">Ejecutivo</option>
                                    <option value="Premium">Premium</option>
                                    <option value="Normal">Normal</option>
                                </select>
                            </div>
                        </div>
                        <div class="row g-3">
                            <div class="col-md-6">
                                <label class="form-label ">Costo Original:</label>
                                <label class="form-control bg-info" id="costo"><%=viaje.getCosto()%></label>
                            </div>

                            <div class="col-md-6">
                                <label class="form-label">Pago a cobrar:</label>
                                <input type="pago" readonly="" name="pago" id="pago" class="form-control">
                            </div>
                        </div>

                        <div class="mb-3">
                            <br>
                            <input type="hidden" name="accion" id="accion" value="guardar" >
                            <button class="btn btn-primary">
                                <input type="hidden" name="nroViaje" value="<%=viaje.getNroViaje()%>">
                                <i class="fa fa-save"></i>&nbsp; Guardar
                            </button>
                        </div>
                    </form>

                </div>
            </div>
        </div>

        <%@include file="include/Js.jsp" %>

        <script>
            function fnCalcularPago() {
                var costo = document.getElementById("costo").innerHTML;
                var tipo = document.getElementById("tipo").value;
                var pago = 0;
                if (tipo === "Turista") {
                    pago = costo * 1.20;
                } else if (tipo === "Ejecutivo") {
                    pago = costo * 1.10;
                } else if (tipo === "Premium") {
                    pago = costo * 1.50;
                } else if (tipo === "Normal") {
                    pago = costo;
                }

                document.getElementById("pago").value = pago;
            }
        </script>
    </body>
</html>
