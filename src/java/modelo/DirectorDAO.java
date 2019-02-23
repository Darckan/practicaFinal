package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList; 

/**
 *
 * @author darck
 */
public class DirectorDAO {
    
    public static boolean exiteDirector( String director ){
        Statement st;
        ResultSet res;
        boolean resultado = false;
        
        // Guardo la consulta SQL realizar en una cadena
        String sql = "select * from directores";
  
        Conexion conexion = new Conexion();
        
        try {
            
            // Preparamos Statement
            st = conexion.getConexion().createStatement(); 
            // Ejecutamos la sentencia y obtenemos la tabla resultado
            res = st.executeQuery(sql);
            // Ahora construimos la lista
            while (res.next()){
                
                if(res.getString("director").equals(director)){
                    resultado = true;
                }
            }
            // Cerramos el recurso PreparedStatement 
            st.close();
            // Cerramos la conexión 
            conexion.cerrarConexion();
            // La inserción se realizó con éxito, devolvemos filas afectadas
        } catch (SQLException e) {
            System.out.println("Problemas durante la consulta en tabla directores");
            System.out.println(e);
            
        }

        return resultado;  
    }
    
    
   
    
}
