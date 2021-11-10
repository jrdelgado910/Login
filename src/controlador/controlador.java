
package controlador;

import conexion.conexionSQL;
import formularios.login;
import formularios.sistema;
import formularios.usuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class controlador implements ActionListener{
    
        
    conexionSQL cc=new conexionSQL();
    Connection con=cc.conexion();
  
    private login log;
    private sistema sis;
    private usuarios user;
    private conexionSQL conex;


    public controlador(login log, sistema sis, usuarios user, conexionSQL conex) {
        this.log = log;
        this.sis = sis;
        this.user = user;
        this.conex = conex;

        this.log.jButton1.addActionListener(this);
        this.sis.jButton1.addActionListener(this);
        this.user.jButton1.addActionListener(this);
    
    }
    
     public void Iniciar(){
        log.pack();
        log.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        log.setVisible(true);
        log.setLocationRelativeTo(null);
     }
     
     public void ValidarUsuarios(){
         String pass=String.valueOf(log.jPasswordField1.getPassword());
        String user=log.jTextField1.getText();
        String SQL="select * from users where user='"+user+"' and pass='"+pass+"'";
        int resul=0;
        
        try {
          Statement st=con.createStatement();
          ResultSet rs=st.executeQuery(SQL);
          if(rs.next()){
              resul=1;
              if(resul==1){
                  sis.setVisible(true);
                  sis.setLocationRelativeTo(null);
                  log.dispose();    
              }
            
        }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error  "+e.getMessage());
        }
    }
     
     public void AgregarUsuarios(){
        String pass=String.valueOf(user.jPasswordField1.getPassword());
        String SQL="insert into users (user,pass) values(?,?)";
        
        try {
            
            PreparedStatement pst=con.prepareStatement(SQL);
            pst.setString(1, user.jTextField1.getText());
            pst.setString(2, pass);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro exitoso");
            user.jTextField1.setText("");
            user.jPasswordField1.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de registro "+e.getMessage());
        }
    }
     
    public void actionPerformed(ActionEvent e) {
           
        if(log.jButton1==e.getSource()){
              ValidarUsuarios();    
        }
        if(sis.jButton1==e.getSource()){
            user.setVisible(true);
            user.setLocationRelativeTo(null);
            sis.dispose();
        }
         if(user.jButton1==e.getSource()){
              AgregarUsuarios();
        }
            
                     
    }
}
