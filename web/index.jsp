
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
        <form method="POST">
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
                   out.print("</td><td>");
                   out.print("<button type='submit' name='borraPeliculas' value='" + pelicula.getId_pelidula() + "' formaction='./BorrarPelicula'>Borrar</button>");
                   out.print("</td><td>");
                   out.print("<button type='submit' name='editPeliculas' value='" + pelicula.getId_pelidula() + "' formaction='./ActualizaPelicula.jsp'>Editar</button>");
                   out.print("</td></tr>");
                }
            %>

        </table>
        </form>
            
        <h1>Directores</h1>
        <form method="POST">
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
                   out.print("</td><td>");
                   out.print("<button type='submit' name='borraDirector' value='" + director.getId_director() + "' formaction='./BorrarDirector'>Borrar</button>");
                   out.print("</td><td>");
                   out.print("<button type='submit' name='editDirector' value='" + director.getId_director() + "' formaction='./ActualizaDirector.jsp'>Editar</button>");
                   out.print("</td></tr>");  
                }
                
            %>
          
        </table>
        </form>
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
                   
        <div>
            <h2>Añadir Director</h2>
            <form action='./InsertDirector' method='POST'>
                <label for="nombreDirect">Nombre</label><input type="text" required name="nombreDirect" id="nombreDirect"/>
                <label for="apeDirect">Apellidos</label><input type="text" required name="apeDirect" id="apeDirect"/>
                <label for="edadDirect">Edad</label><input type="number" required name="edadDirect" id="edadDirect"/> 
                <input type="submit" value="Añadir"/>
            </form>
        </div>
            
        <div>
            <h2>Añadir pelicula</h2>
            <form action='./InsertPelicula' method='POST'>
                <label for="nombrePeli">Nombre</label><input type="text" required name="nombrePeli" id="nombrePeli"/>
                <label for="costesPeli">Costes</label><input type="number" required name="costesPeli" id="costesPeli"/>
                <label for="directorPeli">Director</label>
                <select name="directorPeli" id="directorPeli">
                <%
                    for (Director director : lista2) {
                        out.print("<option value='" + director.getId_director() + "'>" + director.getId_director() + "</option>");  
                     }
                %> 
                <input type="submit" value="Añadir"/>
                </select>          
            </form>
        </div>
 
    </body>
</html>
