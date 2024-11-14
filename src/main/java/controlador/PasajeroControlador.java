package controlador;

import dao.PasajeroDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Pasajero;
import modelo.Viaje;

public class PasajeroControlador extends HttpServlet {

    private PasajeroDAO pasDao = new PasajeroDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String accion = request.getParameter("accion") == null ? "" : request.getParameter("accion");
            switch (accion.toLowerCase()) {
                case "listar":
                    Listar(request, response);
                    break;
                case "guardar":
                    Guardar(request, response);
                    break;
                case "eliminar":
                    Eliminar(request, response);
                    break;
            }
        } catch (Exception ex) {
            response.sendRedirect("ViajeControlador?accion=listar");
        }
    }

    protected void Eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");

        pasDao.Eliminar(id);

        response.sendRedirect("PasajeroControlador?accion=listar");
    }

    protected void Guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Pasajero obj = new Pasajero();
        obj.setNroAsiento(Integer.parseInt(request.getParameter("asiento")));
        obj.setNombres(request.getParameter("nombres"));
        obj.setTipo(request.getParameter("tipo"));
        obj.setPago(Double.parseDouble(request.getParameter("pago")));
        obj.setNroViaje(request.getParameter("nroViaje"));

        pasDao.Guardar(obj);
        response.sendRedirect("PasajeroControlador?accion=listar");
    }

    protected void Listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Viaje viaje = (Viaje) request.getSession().getAttribute("viaje");
        List<Pasajero> listPasajeros = pasDao.ListarTodos(viaje.getNroViaje());

        request.setAttribute("lista", listPasajeros);
        request.getSession().setAttribute("pasajeros", listPasajeros);
        request.getRequestDispatcher("PagListarPasajero.jsp").forward(request, response);
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
