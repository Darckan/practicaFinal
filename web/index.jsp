
<%@page import="modelo.PeliculaDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Pelicula"%>
<%@page import="modelo.Director"%>
<%@page import="modelo.DirectorDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultados</title>
    </head>
    <body>
        <h1>Peliculas</h1>

        <table border ="1">        
            <tr>
                <th>
                    <b>Id</b>
                </th>
                
                <th>
                    <b>Nombre</b>
                </th>
                
                <th>
                    <b>Id_Director</b>
                </th>
                
                <th>
                    <b>Costes</b>
                </th>
            </tr>
 
            <%
                // Lista ordenada por nombre 
                ArrayList<Pelicula> lista = PeliculaDAO.consultarPeliculas();
                for (Pelicula pelicula : lista) {
                   out.print("<tr><td>");
                   out.print(pelicula.getId_pelidula());
                   out.print("</td><td>");
                   out.print(pelicula.getNombre());
                   out.print("</td><td>");
                   out.print(pelicula.getId_director());
                   out.print("</td><td>");
                   out.print(pelicula.getCostes());
                   out.print("</td></tr>");
                }
            %>

        </table>
            
        
            
        <h1>Directores</h1>
        
        <table border ="1">        
            <tr>
                <th>
                    <b>Id</b>
                </th>
                
                <th>
                    <b>Nombre</b>
                </th>
                
                <th>
                    <b>Apellidos</b>
                </th>
                
                <th>
                    <b>Edad</b>
                </th>
            </tr>
            
            <%
                
                // Lista ordenada por nombre 
                ArrayList<Director> lista2 = DirectorDAO.consultarDirectores();
                for (Director director : lista2) {
                   out.print("<tr><td>");
                   out.print(director.getId_director());
                   out.print("</td><td>");
                   out.print(director.getNombre());
                   out.print("</td><td>");
                   out.print(director.getApellidos());
                   out.print("</td><td>");
                   out.print(director.getEdad());
                   out.print("</td></tr>");  
                }
                
            %>
            
        </table>
        <br/>
        <form action='./PeliculasDirector' method='POST'>
            <select name="verPelis">
            <%
                for (Director director : lista2) {
                    out.print("<option value='" + director.getId_director() + "'>" + director.getId_director() + "</option>");  
                 }
            %> 
            <input type="submit" value="Buscar"/>
            </select>          
        </form>
            
        
            
            
        
 
    </body>
</html>
