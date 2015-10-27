/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author edwin molina cardena
 */
public final class Conexion {

    private static Connection connection = null;
    private static Conexion conexion = null;
    public static Statement st;
    public static ResultSet rs;
    public static String query;
    
    public static void abrirConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String Url = "jdbc:mysql://localhost/bdServidor";
            connection = DriverManager.getConnection(Url, "root", "123456");
            if (connection != null) {
                System.out.println("Conexion exitosa");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }
    
    public static void start() {        
            createInstance();        
    }

    private static void createInstance() {
        if (conexion == null) {
            synchronized (Conexion.class) {
                if (conexion == null) {
                    conexion = new Conexion();
                }
            }
        }
    }
   
    public static Connection getConexion() {
        return connection;
    }

    public static void cerrarConexion() {
        try {
            if (connection != null) {
                connection.close();
            }
            connection = null;
            System.out.println("Se Cerro la Coneccion");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: No hay ninguna conexión activa .....");
        }
    }
}
