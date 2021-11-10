
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class conexionSQL {
    Connection conectar=null;
    public Connection conexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conectar=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/ejlogin","root","");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de conexion "+e.getMessage());
        }
        return conectar;
    }
    
}
