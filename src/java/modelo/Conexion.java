package modelo;
/**
 *
 * @author J. Carlos F. Vico <jfervic933@maralboran.es>
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    private static final String SERVIDOR = "jdbc:mysql://localhost/";
    private static final String NOMBRE_BASE_DATOS = "bdpeliculas";
    private static final String USER = "jose";
    private static final String PASS = "josefa";
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    
    private Connection conexion;
    
    public Conexion(){
        
        try {
            Class.forName(DRIVER); 

            // Se crea el objeto Connection	
            conexion = DriverManager.getConnection(SERVIDOR + NOMBRE_BASE_DATOS, USER, PASS);

            System.out.println("Conexión realizada con éxito.");

        } catch (SQLException | ClassNotFoundException e) {
           
            System.out.println(e);
        }
    }
    
    public Connection getConexion() {
        return conexion;
    }
    
    public void cerrarConexion() {
        try {
            conexion.close();
            System.out.println("Cerrando la conexión con la BD.");
        } catch (SQLException e) {
            System.out.println(e);
        }
        conexion = null;
    }
    
}

