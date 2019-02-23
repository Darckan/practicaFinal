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
    
    public static boolean exiteDirector( String nombre, String apellidos ){
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
                
                if(res.getString("nombre").equals(nombre) && res.getString("apellidos").equals(apellidos)){
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
    
    public static int insertarDirector(String nombre, String apellidos, int edad){
        
        // Cadena con la consulta parametrizada
        String sql = "insert into directores values (?,?,?)";

        Conexion conexion = new Conexion();
        
        PreparedStatement prest;

        try { 
            // Preparamos la inserción de datos  mediante un PreparedStatement
            prest = conexion.getConexion().prepareStatement(sql);

            // Procedemos a indicar los valores que queremos insertar
            // Usamos los métodos setXXX(indice, valor)
            // indice indica la posicion del argumento ?, empieza en 1
            // valor es el dato que queremos insertar
            prest.setString(1, nombre);
            prest.setString(2, apellidos);
            prest.setInt(3, edad);

            // Ejecutamos la sentencia de inserción preparada anteriormente
            int nfilas = prest.executeUpdate();
    
            // Cerramos el recurso PreparedStatement 
            prest.close();
            
            // Cerramos la conexión 
            conexion.cerrarConexion();
            // La inserción se realizó con éxito, devolvemos filas afectadas
            return nfilas;
        } catch (SQLException e) {
            System.out.println("Problemas durante la inserción de datos en la tabla directores");
            System.out.println(e);
            return -1;
        }
    }
    
    public static Director consultarDirector(String nombre, String apellidos){
        Statement st;
        ResultSet res;
        Director director = new Director();
        
        // Guardo la consulta SQL realizar en una cadena
        String sql = "select * from directores where nombre ='"+nombre+"' and apellidos='"+apellidos+"'";
  
        Conexion conexion = new Conexion();
        
        try {
            
            // Preparamos Statement
            st = conexion.getConexion().createStatement(); 
            // Ejecutamos la sentencia y obtenemos la tabla resultado
            res = st.executeQuery(sql);
            // Ahora construimos la lista
            director.setId_director(res.getInt("id"));
            director.setNombre(res.getString("nombre"));
            director.setApellidos(res.getString("apellidos"));
            director.setEdad(res.getInt("edad"));
            
            // Cerramos el recurso PreparedStatement 
            st.close();
            // Cerramos la conexión 
            conexion.cerrarConexion();
            // La inserción se realizó con éxito, devolvemos filas afectadas
        } catch (SQLException e) {
            System.out.println("Problemas durante la consulta en tabla directores");
            System.out.println(e);
            
        }

        return director;  
    }
   
    
}
