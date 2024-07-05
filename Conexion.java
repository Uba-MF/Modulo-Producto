
package Producto;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    
    String user = "root";
    String password = "";
    String url = "jdbc:mysql://localhost:3306/";
    String db = "crudjava";
    Connection conexion;
    
    public Connection conectar(){
    
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url+db,user,password);
      
        } catch (ClassNotFoundException|SQLException ex) {
            
        }
        return conexion;
    }
    
    public void desconectar(){
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
