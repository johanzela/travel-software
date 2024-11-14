package controlador;

import dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Usuario;

public class UsuarioControlador extends HttpServlet {

    private UsuarioDAO usuarioDao = new UsuarioDAO();

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
        }
    }

    protected void Eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));

        usuarioDao.Eliminar(id);

        response.sendRedirect("UsuarioControlador?accion=listar");
    }

    protected void EdicionDatos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Usuario obj = new Usuario();
        obj.setCodUsuario(Integer.parseInt(request.getParameter("id")));
        obj.setNombres(request.getParameter("nombre"));
        obj.setApellidos(request.getParameter("apellido"));
        obj.setCorreo(request.getParameter("correo"));
        obj.setPassword(request.getParameter("password"));

        usuarioDao.Editar(obj);
        response.sendRedirect("UsuarioControlador?accion=listar");
    }

    protected void CargarDatos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));

        request.setAttribute("item", usuarioDao.BuscarPorId(id));
        request.getRequestDispatcher("PagEditarUsuario.jsp").forward(request, response);
    }

    protected void Guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Usuario obj = new Usuario();
        obj.setNombres(request.getParameter("nombre"));
        obj.setApellidos(request.getParameter("apellido"));
        obj.setCorreo(request.getParameter("correo"));
        obj.setPassword(request.getParameter("password"));

        usuarioDao.Guardar(obj);
        response.sendRedirect("UsuarioControlador?accion=listar");
    }

    protected void Listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        request.setAttribute("lista", usuarioDao.ListarTodos());
        request.getRequestDispatcher("PagListarUsuario.jsp").forward(request, response);
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
