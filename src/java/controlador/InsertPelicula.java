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
import modelo.PeliculaDAO;
/**
 *
 * @author darck
 */
public class InsertPelicula extends HttpServlet{
    
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Se establece el tipo de contenido a enviar en la respuesta
        response.setContentType("text/html;charset=UTF-8");
       
        // Obtengo la sesion de la petición HTTP, si existe. 
        // Con true, si no está creada se crea
        HttpSession sesion = request.getSession(true);
        
        String nombrePeli = request.getParameter("nombrePeli");
        int costesPeli = Integer.parseInt(request.getParameter("costesPeli"));
        int directorPeli = Integer.parseInt(request.getParameter("directorPeli"));
        int filas = 0;

        if (PeliculaDAO.exitePelicula(nombrePeli)){
            response.sendRedirect(response.encodeRedirectURL("ErrorPeli.jsp"));
        }else{
            filas = PeliculaDAO.insertarPelicula(nombrePeli, directorPeli, costesPeli);
        }

        // Una vez realizada la operación, redirigimos a la vista
        response.sendRedirect(response.encodeRedirectURL("index.jsp?" + filas));
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
