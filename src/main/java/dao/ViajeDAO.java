package dao;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Ruta;
import modelo.Viaje;

public class ViajeDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public ArrayList<Viaje> ListarTodos() {
        ArrayList<Viaje> lista = new ArrayList<>();

        try {
            String sql = "select r.codRuta,nomRuta,imagenRuta,nroViaje,fecha,hora,costo "
                    + "from Ruta r inner join Viaje v"
                    + " on v.codRuta = r.codRuta";
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Viaje c = new Viaje();
                c.setCodRuta(rs.getString(1));
                c.setNomRuta(rs.getString(2));
                c.setImagenRuta(rs.getString(3));
                c.setNroViaje(rs.getString(4));
                c.setFecha(rs.getString(5));
                c.setHora(rs.getString(6));
                c.setCosto(rs.getDouble(7));
                lista.add(c);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public int Guardar(Viaje p) {
        int res = 0;
        String sql = "{call sp_insert_viaje(?,?,?,?)}";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getCodRuta());
            ps.setString(2, p.getFecha());
            ps.setString(3, p.getHora());
            ps.setDouble(4, p.getCosto());
            res = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public Viaje BuscarPorId(String id) {
        Viaje c = null;

        try {
            String sql = "select r.codRuta,nomRuta,imagenRuta,nroViaje,fecha,hora,costo "
                    + "from Ruta r inner join Viaje v"
                    + " on v.codRuta = r.codRuta"
                    + " where nroViaje=?";
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                c = new Viaje();
                c.setCodRuta(rs.getString(1));
                c.setNomRuta(rs.getString(2));
                c.setImagenRuta(rs.getString(3));
                c.setNroViaje(rs.getString(4));
                c.setFecha(rs.getString(5));
                c.setHora(rs.getString(6));
                c.setCosto(rs.getDouble(7));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return c;
    }

    public int Editar(Viaje p) {
        int res = 0;
        String sql = "update Viaje set codRuta=?,fecha=?,hora=?,costo=? "
                + " where nroViaje=?";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getCodRuta());
            ps.setString(2, p.getFecha());
            ps.setString(3, p.getHora());
            ps.setDouble(4, p.getCosto());
            ps.setString(5, p.getNroViaje());
            res = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public int Eliminar(String id) {
        int res = 0;
        String sql = "delete from Viaje "
                + " where nroViaje=?";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            res = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
