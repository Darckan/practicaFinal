package controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.DirectorDAO;
import modelo.PeliculaDAO;
import modelo.Pelicula;
import modelo.Director;
/**
 *
 * @author darck
 */
public class Controlador extends HttpServlet{
    
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Se establece el tipo de contenido a enviar en la respuesta
        response.setContentType("text/html;charset=UTF-8");
       
        // Obtengo la sesion de la petición HTTP, si existe. 
        // Con true, si no está creada se crea
        HttpSession sesion = request.getSession(true);
        
        // Obtenemos el valor de el campo accion
        String accion = request.getParameter("accion");
        
        // Segun el valor de accion realizaremos una acción u otra 
        if(accion.equals("editarPelicula1")){
            // Obtenemos el valor del campo editarPeliculas
            String editPeliculas = request.getParameter("editPeliculas");
            
            // Establecemos como parametro de sesion el valor anterior
            sesion.setAttribute("editPeliculas", editPeliculas);
            
            // Redireccionamos a editPelicula.jsp
            response.sendRedirect(response.encodeRedirectURL("editPelicula.jsp"));
        }else if(accion.equals("editarPelicula2")){
             // Obtenemos el valor de los campos, editarPeliculas, nombreEditPeli, costesEditPeli, directorEditPeli
            int editPeliculas = Integer.parseInt(request.getParameter("editPeliculas"));
            String nombreEditPeli = request.getParameter("nombreEditPeli");
            int costesEditPeli = Integer.parseInt(request.getParameter("costesEditPeli"));
            int directorEditPeli = Integer.parseInt(request.getParameter("directorEditPeli"));
            int filas = 0;
            
            //Obtenemos un objeto usando consultarPelicula
            Pelicula comprobante = PeliculaDAO.consultarPelicula(editPeliculas);
            
            
            if(comprobante.getNombre().equals(nombreEditPeli)){
                // Si el nombre es igual ejecutamos la inserción
                filas = PeliculaDAO.actualizarPelicula(editPeliculas, directorEditPeli, nombreEditPeli, costesEditPeli );
                // Redireccionamos a index.jsp y mostramos por url el numero de filas resultantes
                response.sendRedirect(response.encodeRedirectURL("index.jsp?" + filas));
            }else{
                // Si el nombre es diferente comprobamos si ya existe
                if (PeliculaDAO.exitePelicula(nombreEditPeli)){
                    // Si existe lo redirigimos a ErrorPeli.jsp
                    response.sendRedirect(response.encodeRedirectURL("ErrorPeli.jsp"));
                }else{
                    // Si no existe realizamos la inserción
                    filas = PeliculaDAO.actualizarPelicula(editPeliculas, directorEditPeli, nombreEditPeli, costesEditPeli );
                    // Una vez realizada la operación, redirigimos a la vista
                    response.sendRedirect(response.encodeRedirectURL("index.jsp?" + filas));
                }
            }
            
        } else if(accion.equals("editarDirector1")){
            // Obtenemos el valor del campo editDirectores
            String editDirectores = request.getParameter("editDirectores");
            
            // Establecemos como parametro de sesion el valor anterior
            sesion.setAttribute("editDirectores", editDirectores);
            
            // Redireccionamos a editPelicula.jsp
            response.sendRedirect(response.encodeRedirectURL("editDirector.jsp"));
        } else if(accion.equals("editarDirector2")){
            
            // Obtenemos el valor de los campos, editDirectores, nombreEditDirector, apellidosEditDirector, edadEditDirector
            int editDirectores = Integer.parseInt(request.getParameter("editDirectores"));
            String nombreEditDirector = request.getParameter("nombreEditDirector");
            String apellidosEditDirector = request.getParameter("apellidosEditDirector");
            int edadEditDirector = Integer.parseInt(request.getParameter("edadEditDirector"));
            int filas = 0;
            
            //Obtenemos un objeto usando consultarDirector
            Director comprobante = DirectorDAO.consultarDirector(editDirectores);
            
            if(comprobante.getNombre().equals(nombreEditDirector) && comprobante.getApellidos().equals(apellidosEditDirector)){
                // Si el nombre y el apellido es igual ejecutamos la inserción
                filas = DirectorDAO.actualizarDirector(editDirectores, nombreEditDirector, apellidosEditDirector, edadEditDirector );
                // Redireccionamos a index.jsp y mostramos por url el numero de filas resultantes
                response.sendRedirect(response.encodeRedirectURL("index.jsp?" + filas));
            }else{
                // Si el nombre y el apellido es diferente comprobamos si ya existe
                if (DirectorDAO.exiteDirector(nombreEditDirector, apellidosEditDirector)){
                    // Si existe lo redirigimos a ErrorDire.jsp
                    response.sendRedirect(response.encodeRedirectURL("ErrorDire.jsp"));
                }else{
                    // Si no existe realizamos la inserción
                    filas = DirectorDAO.actualizarDirector(editDirectores, nombreEditDirector, apellidosEditDirector, edadEditDirector );
                    // Una vez realizada la operación, redirigimos a la vista
                    response.sendRedirect(response.encodeRedirectURL("index.jsp?" + filas));
                }
            }

        } else if(accion.equals("borrarPelicula")){
            
            // Obtenemos el valor del campo borraPeliculas         
            int id_pelicula = Integer.parseInt(request.getParameter("borraPeliculas"));
            
            //Ejecutamos el borrado
            int filas = PeliculaDAO.borrarPelicula(id_pelicula);

            // Una vez realizada la operación, redirigimos a la vista
            response.sendRedirect(response.encodeRedirectURL("index.jsp?" + filas));

        } else if(accion.equals("borrarDirector")){
            
            // Obtenemos el valor del campo borraDirector      
            int id_director = Integer.parseInt(request.getParameter("borraDirector"));
            
            //Ejecutamos el borrado
            int filas = DirectorDAO.borrarDirector(id_director);

            // Una vez realizada la operación, redirigimos a la vista
            response.sendRedirect(response.encodeRedirectURL("index.jsp?" + filas));

        } else if(accion.equals("verPeliculasDirector")){
            
            // Obtenemos el valor del campo verPelis  
            String directorId = request.getParameter("verPelis");
        
            // Asigno ese id del director al atributo de la sesión y así lo puedo usar en la vista
            sesion.setAttribute("directorId", directorId);

            // Una vez realizada la operación, redirigimos a la vista
            response.sendRedirect(response.encodeRedirectURL("PeliculasDeDirector.jsp"));

        } else if(accion.equals("insertDirector")){
            
            // Obtenemos el valor de los campos nombreDirect, apeDirect, edadDirect
            String nombreDirect = request.getParameter("nombreDirect");
            String apeDirect = request.getParameter("apeDirect");
            int edadDirect = Integer.parseInt(request.getParameter("edadDirect"));
            int filas = 0;
            
            // Comprobamos si el nombre y el apellido ya existe
            if (DirectorDAO.exiteDirector(nombreDirect, apeDirect)){
                
                // Si existe redirigimos a ErrorDire.jsp
                response.sendRedirect(response.encodeRedirectURL("ErrorDire.jsp"));
            }else{
                // Si no existe realizamos la inserción
                filas = DirectorDAO.insertarDirector(nombreDirect, apeDirect, edadDirect);
                // Una vez realizada la operación, redirigimos a la vista
                response.sendRedirect(response.encodeRedirectURL("index.jsp?" + filas));
            }   
            
        } else if(accion.equals("insertPelicula")){
            
            // Obtenemos el valor de los campos nombrePeli, costesPeli, directorPeli
            String nombrePeli = request.getParameter("nombrePeli");
            int costesPeli = Integer.parseInt(request.getParameter("costesPeli"));
            int directorPeli = Integer.parseInt(request.getParameter("directorPeli"));
            int filas = 0;
            
            // Comprobamos si el nombre ya existe
            if (PeliculaDAO.exitePelicula(nombrePeli)){
                
                // Si existe redirigimos a ErrorPeli.jsp
                response.sendRedirect(response.encodeRedirectURL("ErrorPeli.jsp"));
            }else{
                // Si no existe realizamos la inserción
                filas = PeliculaDAO.insertarPelicula(nombrePeli, directorPeli, costesPeli);
                // Una vez realizada la operación, redirigimos a la vista
                response.sendRedirect(response.encodeRedirectURL("index.jsp?" + filas));
            }
    
        } else{
            // Si no se da ninguno de los casos anteriores redirigimos a index.jsp?algofallo
            response.sendRedirect(response.encodeRedirectURL("index.jsp?algofallo"));
        }

        
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
}