package dao;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Ruta;

public class RutaDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public ArrayList<Ruta> ListarTodos() {
        ArrayList<Ruta> lista = new ArrayList<>();

        try {
            String sql = "select codRuta,nomRuta,imagenRuta from Ruta";
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ruta c = new Ruta();
                c.setCodRuta(rs.getString(1));
                c.setNomRuta(rs.getString(2));
                c.setImagenRuta(rs.getString(3));
                lista.add(c);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public int Guardar(Ruta p) {
        int res = 0;
        String sql = "{call sp_insert_ruta(?,?)}";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNomRuta());
            ps.setString(2, p.getImagenRuta());

            res = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public Ruta BuscarPorId(String id) {
        Ruta c = null;

        try {
            String sql = "select codRuta,nomRuta,imagenRuta "
                    + " from Ruta"
                    + " where codRuta=?";
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                c = new Ruta();
                c.setCodRuta(rs.getString(1));
                c.setNomRuta(rs.getString(2));
                c.setImagenRuta(rs.getString(3));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return c;
    }

    public int Editar(Ruta p) {
        int res = 0;
        String sql = "update Ruta set nomRuta=?,imagenRuta=?"
                + " where codRuta=?";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNomRuta());
            ps.setString(2, p.getImagenRuta());
            ps.setString(3, p.getCodRuta());
            res = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    
     public int Eliminar(String id) {
        int res = 0;
        String sql = "delete from Ruta "
                + " where codRuta=?";
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
