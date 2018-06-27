/*
 * Estableciendo conexion
 */
package conexion;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;

/**
 *
 * @author LN710Q
 */
public class Conexion {

    private String user;
    private String pass;
    private String driver;
    private String url;
    private Connection cnx;

    public static Conexion instance;

    public synchronized static Conexion conectar() {
        if (instance == null) {
            return new Conexion();
        }
        return instance;
    }

    private Conexion() {
        cargarCredenciales();
        try {
            Class.forName(this.driver);
            cnx = (Connection) DriverManager.getConnection(this.url, this.user, this.pass);
          
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("xzdccc");
        }
    }

    private void cargarCredenciales() {
        user = "root";
        pass = "";
        driver = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://localhost/cortomiercoles";
    }

    public Connection getCnx() {
        return cnx;
    }
    public void cerrarConexion(){
        instance=null;
    }
}

