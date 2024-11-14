<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    .efecto_boton{
        margin-left:10px; 
        border:none; 
        color: black;
    }
    .efecto_boton:hover{
        background: #0066cc;
        color: white;
    }
</style>
<nav class="navbar navbar-expand-lg bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#" style="color: white;">Viaje Seguro</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link btn btn-outline-warning efecto_boton" href="RutaControlador?accion=listar">Rutas</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link btn btn-outline-warning efecto_boton" href="ViajeControlador?accion=listar">Viajes</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link btn btn-outline-warning efecto_boton" href="UsuarioControlador?accion=listar">Usuarios</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link btn btn-outline-warning efecto_boton" href="PagReporte.jsp">Reportes</a>
                </li>
            </ul>

            <div class="dropdown">
                <button class="btn btn-outline-primary dropdown-toggle efecto_boton" data-bs-toggle="dropdown"><i class="fa-solid fa-user-tie"></i> 
                    ${sessionScope.usuario.nomCompletos()}
                </button>
                <ul class="dropdown-menu text-center">

                    <form action="AuthControlador" method="post">
                        <input type="hidden" name="accion" value="logout">
                        <button name="accion" value="Salir" class="dropdown-item" href="#"><i class="fa-solid fa-right-from-bracket"></i> Salir</button>
                    </form>
                </ul>
            </div>

        </div>
    </div>
</nav>

