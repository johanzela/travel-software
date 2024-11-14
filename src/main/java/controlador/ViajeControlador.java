package controlador;

import dao.ViajeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.*;

public class ViajeControlador extends HttpServlet {

    private ViajeDAO viajeDao = new ViajeDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion") == null ? "" : request.getParameter("accion");
        switch (accion.toLowerCase()) {
            case "listar":
                Listar(request, response);
                break;
            case "guardar":
                Guardar(request, response);
                break;
            case "editar":
                CargarDatos(request, response);
                break;
            case "edicion":
                EdicionDatos(request, response);
                break;
            case "eliminar":
                Eliminar(request, response);
                break;
            case "ver_pasajeros":
                verPasajeros(request, response);
                break;
        }
    }

    protected void verPasajeros(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String id = request.getParameter("id");
        Viaje viaje = viajeDao.BuscarPorId(id);
        request.getSession().setAttribute("viaje", viaje);

        response.sendRedirect("PasajeroControlador?accion=listar");
    }

    protected void Eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");

        viajeDao.Eliminar(id);

        response.sendRedirect("ViajeControlador?accion=listar");
    }

    protected void EdicionDatos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Viaje obj = new Viaje();
        obj.setCodRuta(request.getParameter("ruta"));
        obj.setFecha(request.getParameter("fecha"));
        obj.setHora(request.getParameter("hora"));
        obj.setCosto(Double.parseDouble(request.getParameter("costo")));
        obj.setNroViaje(request.getParameter("id"));

        viajeDao.Editar(obj);
        response.sendRedirect("ViajeControlador?accion=listar");
    }

    protected void CargarDatos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        Viaje obj = viajeDao.BuscarPorId(id);
        request.setAttribute("item", obj);
        request.getRequestDispatcher("PagEditarViaje.jsp").forward(request, response);
    }

    protected void Guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Viaje obj = new Viaje();
        obj.setCodRuta(request.getParameter("ruta"));
        obj.setFecha(request.getParameter("fecha"));
        obj.setHora(request.getParameter("hora"));
        obj.setCosto(Double.parseDouble(request.getParameter("costo")));
        viajeDao.Guardar(obj);
        response.sendRedirect("ViajeControlador?accion=listar");
    }

    protected void Listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        request.setAttribute("lista", viajeDao.ListarTodos());
        request.getRequestDispatcher("PagListarViaje.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
