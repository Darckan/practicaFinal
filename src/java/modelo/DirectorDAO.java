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
        String sql = "insert into directores (nombre, apellidos, edad) values (?,?,?)";

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
    
    public static ArrayList<Director> consultarDirectores(){
        Statement st;
        ResultSet res;
        ArrayList<Director> lista = new ArrayList();
        
        // Guardo la consulta SQL realizar en una cadena
        String sql = "select * from directores order by nombre";
  
        Conexion conexion = new Conexion();
        
        try {
            
            // Preparamos Statement
            st = conexion.getConexion().createStatement(); 
            // Ejecutamos la sentencia y obtenemos la tabla resultado
            res = st.executeQuery(sql);
            
            // Ahora construimos la lista
            while (res.next()){
                Director director = new Director();
                // Recogemos los datos del turismo, guardamos en un objeto
                director.setId_director(res.getInt("id_director"));
                director.setNombre(res.getString("nombre"));
                director.setApellidos(res.getString("apellidos"));
                director.setEdad(res.getInt("edad"));

                //Añadimos el objeto al array
                lista.add(director);
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

        return lista;  
    }
    
    
    public static int actualizarDirector(int id_director, String nombre, String apellidos, int edad){
        // Cadena con la consulta 
        String sql = "update directores set nombre ='" + nombre + "', apellidos ='" + apellidos + "', edad ='" + edad + "' where id_director = '" + id_director + "'";
        Conexion conexion = new Conexion();
        try {

            int nfilas;
            // Ejecutamos la sentencia de modificación
            //try-with-resources
            try (Statement prest = conexion.getConexion().createStatement()) {
                // Ejecutamos la sentencia de modificación
                nfilas = prest.executeUpdate(sql);
                // Cerramos el recurso PreparedStatement
            }
            // Cerramos la conexión 
            conexion.cerrarConexion();
            // La inserción se realizó con éxito, devolvemos filas afectadas
            return nfilas;
        } catch (SQLException e) {
            System.out.println("Problemas durante la modificación de datos en la tabla directores");
            System.out.println(e);
            return -1;
        }
    }
    
    public static int borrarDirector(int id_director){
        // Cadena con la consulta 
        String sql = "delete from directores where id_director = '" + id_director + "'";
        Conexion conexion = new Conexion();
        try {

            int nfilas;
            // Ejecutamos la sentencia de modificación
            //try-with-resources
            try (Statement prest = conexion.getConexion().createStatement()) {
                // Ejecutamos la sentencia de modificación
                nfilas = prest.executeUpdate(sql);
                // Cerramos el recurso PreparedStatement
            }
            // Cerramos la conexión 
            conexion.cerrarConexion();
            // La inserción se realizó con éxito, devolvemos filas afectadas
            return nfilas;
        } catch (SQLException e) {
            System.out.println("Problemas durante la modificación de datos en la tabla directores");
            System.out.println(e);
            return -1;
        }
    }    
}
