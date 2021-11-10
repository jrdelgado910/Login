
package principal;

import conexion.conexionSQL;
import controlador.*;
import formularios.*;

public class principal {
    
    public static void main(String[] args) {
        login log = new login();
        sistema sis = new sistema();
        usuarios user = new usuarios();
        conexionSQL conex = new conexionSQL();
        controlador con = new controlador(log, sis, user, conex);
        con.Iniciar();
        
    }
    
}
