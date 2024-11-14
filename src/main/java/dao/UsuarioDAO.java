package dao;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Usuario;

public class UsuarioDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public Usuario Login(String correo, String clave) {
        Usuario e = null;
        try {
            String sql = "select nombres,apellidos from Usuario where correo=? and password=? ";
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, clave);
            rs = ps.executeQuery();

            if (rs.next()) {
                e = new Usuario();
                e.setNombres(rs.getString("nombres"));
                e.setApellidos(rs.getString("apellidos"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return e;
    }

    public ArrayList<Usuario> ListarTodos() {
        ArrayList<Usuario> lista = new ArrayList<>();

        try {
            String sql = "select codUsuario,nombres,apellidos,correo,password "
                    + " from Usuario";
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario c = new Usuario();
                c.setCodUsuario(rs.getInt(1));
                c.setNombres(rs.getString(2));
                c.setApellidos(rs.getString(3));
                c.setCorreo(rs.getString(4));
                c.setPassword(rs.getString(5));
                lista.add(c);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public int Guardar(Usuario p) {
        int res = 0;
        String sql = "insert into Usuario(nombres,apellidos,correo,password) values(?,?,?,?)";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombres());
            ps.setString(2, p.getApellidos());
            ps.setString(3, p.getCorreo());
            ps.setString(4, p.getPassword());
            res = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public Usuario BuscarPorId(int id) {
        Usuario c = null;

        try {
            String sql = "select codUsuario,nombres,apellidos,correo,password "
                    + " from Usuario"
                    + " where codUsuario=?";
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                c = new Usuario();
                c.setCodUsuario(rs.getInt(1));
                c.setNombres(rs.getString(2));
                c.setApellidos(rs.getString(3));
                c.setCorreo(rs.getString(4));
                c.setPassword(rs.getString(5));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return c;
    }

    public int Editar(Usuario p) {
        int res = 0;
        String sql = "update Usuario set nombres=?,apellidos=?,correo=?,password=? "
                + " where codUsuario=?";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombres());
            ps.setString(2, p.getApellidos());
            ps.setString(3, p.getCorreo());
            ps.setString(4, p.getPassword());
            ps.setInt(5, p.getCodUsuario());
            res = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public int Eliminar(int id) {
        int res = 0;
        String sql = "delete from Usuario "
                + " where codUsuario=?";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            res = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
