package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static Connection con;
    private static String url = "jdbc:mysql://localhost:3306/";
    private static String bd = "BDSistViaje";
    private static String user = "root";
    private static String pass = "";

    public static Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url + bd, user, pass);
            System.out.println("Conexion Exitosa");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
