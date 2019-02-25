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
        
        String accion = request.getParameter("accion");
        
        if(accion.equals("editarPelicula1")){
            
            String editPeliculas = request.getParameter("editPeliculas");
            sesion.setAttribute("editPeliculas", editPeliculas);
            
            response.sendRedirect(response.encodeRedirectURL("editPelicula.jsp"));
        }else if(accion.equals("editarPelicula2")){
            
            int editPeliculas = Integer.parseInt(request.getParameter("editPeliculas"));
            String nombreEditPeli = request.getParameter("nombreEditPeli");
            int costesEditPeli = Integer.parseInt(request.getParameter("costesEditPeli"));
            int directorEditPeli = Integer.parseInt(request.getParameter("directorEditPeli"));

            int filas = PeliculaDAO.actualizarPelicula(editPeliculas, directorEditPeli, nombreEditPeli, costesEditPeli );
            
            response.sendRedirect(response.encodeRedirectURL("index.jsp?" + filas));

            // Una vez realizada la operación, redirigimos a la vista
        } else if(accion.equals("editarDirector1")){
            
            String editDirectores = request.getParameter("editDirectores");
            sesion.setAttribute("editDirectores", editDirectores);
            
            response.sendRedirect(response.encodeRedirectURL("editDirector.jsp"));
        }else if(accion.equals("editarDirector2")){
            
            int editDirectores = Integer.parseInt(request.getParameter("editDirectores"));
            String nombreEditDirector = request.getParameter("nombreEditDirector");
            String apellidosEditDirector = request.getParameter("apellidosEditDirector");
            int edadEditDirector = Integer.parseInt(request.getParameter("edadEditDirector"));

            int filas = DirectorDAO.actualizarDirector(editDirectores, nombreEditDirector, apellidosEditDirector, edadEditDirector );
            
            response.sendRedirect(response.encodeRedirectURL("index.jsp?" + filas));

            // Una vez realizada la operación, redirigimos a la vista
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