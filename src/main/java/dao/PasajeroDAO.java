package dao;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.*;

public class PasajeroDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public ArrayList<Pasajero> ListarTodos(String nro) {
        ArrayList<Pasajero> lista = new ArrayList<>();

        try {
            String sql = "select r.codRuta,nomRuta,imagenRuta,v.nroViaje,fecha,hora,costo,"
                    + " nroBoleto,nombres,nroAsiento,tipo,pago "
                    + " from Ruta r inner join Viaje v "
                    + " on v.codRuta = r.codRuta"
                    + " inner join Pasajero p"
                    + " on p.nroViaje = v.nroViaje"
                    + " where  v.nroViaje=?";
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, nro);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pasajero c = new Pasajero();
                c.setCodRuta(rs.getString(1));
                c.setNomRuta(rs.getString(2));
                c.setImagenRuta(rs.getString(3));
                c.setNroViaje(rs.getString(4));
                c.setFecha(rs.getString(5));
                c.setHora(rs.getString(6));
                c.setCosto(rs.getDouble(7));
                c.setNroBoleto(rs.getString(8));
                c.setNombres(rs.getString(9));
                c.setNroAsiento(rs.getInt(10));
                c.setTipo(rs.getString(11));
                c.setPago(rs.getDouble(12));
                lista.add(c);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public int Guardar(Pasajero p) {
        int res = 0;
        String sql = "{call sp_insert_pasajero(?,?,?,?,?)}";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNroViaje());
            ps.setString(2, p.getNombres());
            ps.setInt(3, p.getNroAsiento());
            ps.setString(4, p.getTipo());
            ps.setDouble(5, p.getPago());
            res = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public int Eliminar(String id) {
        int res = 0;
        String sql = "delete from Pasajero where nroBoleto=?";
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
