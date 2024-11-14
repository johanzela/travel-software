package dao;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.*;

public class ReporteDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public ArrayList<Reporte> ListarCantTipo(int annio) {
        ArrayList<Reporte> lista = new ArrayList<>();

        try {
            String sql = "select tipo , count(1)\n"
                    + "from viaje v inner join Pasajero p on p.nroViaje = v.nroViaje\n"
                    + "where YEAR(fecha) = ?\n"
                    + "group by tipo";
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, annio);
            rs = ps.executeQuery();
            while (rs.next()) {
                Reporte c = new Reporte();
                c.setDescripcion(rs.getString(1));
                c.setCantidad(rs.getInt(2));
                lista.add(c);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public ArrayList<Reporte> ListarCantViajePorRuta(int annio) {
        ArrayList<Reporte> lista = new ArrayList<>();

        try {
            String sql = "select nomRuta , count(1)\n"
                    + "from viaje v inner join Ruta r\n"
                    + "on r.codRuta = v.codRuta\n"
                    + "where YEAR(fecha) = ?\n "
                    + "group by nomRuta";
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, annio);
            rs = ps.executeQuery();
            while (rs.next()) {
                Reporte c = new Reporte();
                c.setDescripcion(rs.getString(1));
                c.setCantidad(rs.getInt(2));
                lista.add(c);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public ArrayList<Reporte> ListarPagoTotalPorViaje(int annio, int mes) {
        ArrayList<Reporte> lista = new ArrayList<>();

        try {
            String sql = "select v.nroViaje , SUM(p.pago)\n"
                    + "from viaje v inner join Pasajero p on p.nroViaje = v.nroViaje\n"
                    + "where YEAR(fecha) = ? AND MONTH(fecha) = ?\n"
                    + "group by v.nroViaje";
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, annio);
            ps.setInt(2, mes);
            rs = ps.executeQuery();
            while (rs.next()) {
                Reporte c = new Reporte();
                c.setDescripcion(rs.getString(1));
                c.setTotal(rs.getDouble(2));
                lista.add(c);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}
