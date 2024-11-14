package controlador;

import dao.ReporteDAO;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Reporte;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class ReporteControlador extends HttpServlet {

    private ReporteDAO reporteDAO = new ReporteDAO();
    private int annioActual = 0;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion") == null ? "" : request.getParameter("accion");
        annioActual = Calendar.getInstance().get(Calendar.YEAR);

        switch (accion.toLowerCase()) {
            case "reporte1":
                Reporte1(request, response);
                break;
            case "reporte2":
                Reporte2(request, response);
                break;
            case "reporte3":
                Reporte3(request, response);
                break;
        }
    }

    protected void Reporte1(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("image/png");
        OutputStream out = response.getOutputStream();
        DefaultPieDataset data = new DefaultPieDataset();
        ArrayList<Reporte> lista = reporteDAO.ListarCantTipo(annioActual);

        for (Reporte r : lista) {
            data.setValue(r.getDescripcion() + "(" + r.getCantidad() + ")", r.getCantidad());
        }
        JFreeChart chart = ChartFactory.createPieChart3D("Cant. tipo de pasajeros de viajes del año " + annioActual,
                data, true, true, false);

        ChartUtilities.writeChartAsJPEG(out, chart, 600, 520);
        out.close();
    }

    protected void Reporte2(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("image/png");
        OutputStream out = response.getOutputStream();
        DefaultPieDataset data = new DefaultPieDataset();

        ArrayList<Reporte> lista = reporteDAO.ListarCantViajePorRuta(annioActual);

        for (Reporte r : lista) {
            data.setValue(r.getDescripcion() + "(" + r.getCantidad() + ")", r.getCantidad());
        }
        JFreeChart chart = ChartFactory.createPieChart3D("Cant. de viajes por ruta del año " + annioActual,
                data, true, true, false);

        ChartUtilities.writeChartAsJPEG(out, chart, 600, 520);
        out.close();
    }

    protected void Reporte3(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("image/png");
        OutputStream out = response.getOutputStream();
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        int annio = Integer.parseInt(request.getParameter("annio"));
        int mes = Integer.parseInt(request.getParameter("mes"));

        ArrayList<Reporte> lista = reporteDAO.ListarPagoTotalPorViaje(annio, mes);

        for (Reporte r : lista) {
            data.setValue(r.getTotal(), r.getDescripcion(), "");
        }
        JFreeChart chart = ChartFactory.createBarChart3D("Pago Total por viaje del "
                + String.format("%02d", mes) + "/" + String.format("%04d", annio),
                "Nro Viaje", "Monto Total (S/)", data, PlotOrientation.VERTICAL, true, true, true);
        ChartUtilities.writeChartAsJPEG(out, chart, 1200, 530);
        out.close();
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
