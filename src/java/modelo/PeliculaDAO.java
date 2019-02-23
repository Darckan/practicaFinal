/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class PeliculaDAO {
    
    public static boolean exitePelicula( String nombre ){
        Statement st;
        ResultSet res;
        boolean resultado = false;
        
        // Guardo la consulta SQL realizar en una cadena
        String sql = "select * from peliculas";
  
        Conexion conexion = new Conexion();
        
        try {
            
            // Preparamos Statement
            st = conexion.getConexion().createStatement(); 
            // Ejecutamos la sentencia y obtenemos la tabla resultado
            res = st.executeQuery(sql);
            // Ahora construimos la lista
            while (res.next()){
                
                if(res.getString("nombre").equals(nombre)){
                    resultado = true;
                }
            }
            // Cerramos el recurso PreparedStatement 
            st.close();
            // Cerramos la conexión 
            conexion.cerrarConexion();
            // La inserción se realizó con éxito, devolvemos filas afectadas
        } catch (SQLException e) {
            System.out.println("Problemas durante la consulta en tabla peliculas");
            System.out.println(e);
            
        }

        return resultado;  
    }
    
    public static int insertarDirector(String nombre, int id_director, int costes){
        
        // Cadena con la consulta parametrizada
        String sql = "insert into peliculas values (?,?,?)";

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
            prest.setInt(2, id_director);
            prest.setInt(3, costes);

            // Ejecutamos la sentencia de inserción preparada anteriormente
            int nfilas = prest.executeUpdate();
    
            // Cerramos el recurso PreparedStatement 
            prest.close();
            
            // Cerramos la conexión 
            conexion.cerrarConexion();
            // La inserción se realizó con éxito, devolvemos filas afectadas
            return nfilas;
        } catch (SQLException e) {
            System.out.println("Problemas durante la inserción de datos en la tabla peliculas");
            System.out.println(e);
            return -1;
        }
    }
    
    public static ArrayList<Pelicula> consultarPeliculas(){
        Statement st;
        ResultSet res;
        ArrayList<Pelicula> lista = new ArrayList();
        
        // Guardo la consulta SQL realizar en una cadena
        String sql = "select * from peliculas order by nombre";
  
        Conexion conexion = new Conexion();
        
        try {
            
            // Preparamos Statement
            st = conexion.getConexion().createStatement(); 
            // Ejecutamos la sentencia y obtenemos la tabla resultado
            res = st.executeQuery(sql);
            
            // Ahora construimos la lista
            while (res.next()){
                Pelicula pelicula = new Pelicula();
                // Recogemos los datos del turismo, guardamos en un objeto
                pelicula.setId_pelidula(res.getInt("id_pelidula"));
                pelicula.setId_director(res.getInt("id_director"));
                pelicula.setNombre(res.getString("nombre"));
                pelicula.setCostes(res.getInt("costes"));

                //Añadimos el objeto al array
                lista.add(pelicula);
            }
            
            // Cerramos el recurso PreparedStatement 
            st.close();
            // Cerramos la conexión 
            conexion.cerrarConexion();
            // La inserción se realizó con éxito, devolvemos filas afectadas
        } catch (SQLException e) {
            System.out.println("Problemas durante la consulta en tabla peliculas");
            System.out.println(e);
            
        }

        return lista;  
    }
    
    
    public static int actualizarDirector(int id_pelicula ,int id_director, String nombre, int costes){
        // Cadena con la consulta 
        String sql = "update peliculas set id_director='" + id_director + "' nombre ='" + nombre + "', costes ='" + costes + "' where id_pelicula like '" + id_pelicula + "'";
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
            System.out.println("Problemas durante la modificación de datos en la tabla peliculas");
            System.out.println(e);
            return -1;
        }
    }
}