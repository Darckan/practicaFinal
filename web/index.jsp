
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
        <link href="./css/style.css" rel="stylesheet" type="text/css">
        <title>Peliculas</title>
    </head>
    <body>
        <%
                ArrayList<Pelicula> lista = PeliculaDAO.consultarPeliculas();
                ArrayList<Director> lista2 = DirectorDAO.consultarDirectores();               
        %>
        <div id="peliculas">
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
                    for (Pelicula pelicula : lista) {
                       out.print("<tr><td>");
                       out.print(pelicula.getId_pelicula());
                       out.print("</td><td>");
                       out.print(pelicula.getNombre());
                       out.print("</td><td>");
                       out.print(pelicula.getId_director());
                       out.print("</td><td>");
                       out.print(pelicula.getCostes());
                       out.print("</td><td>");
                       out.print("<form method='POST' action='./Controlador'><button type='submit' name='borraPeliculas' value='" + pelicula.getId_pelicula() + "'>Borrar</button>");
                       out.print("<input type='hidden' name='accion' value='borrarPelicula'/> </form>");
                       out.print("</td><td>");
                       out.print("<form method='POST' action='./Controlador'><button type='submit' name='editPeliculas' value='" + pelicula.getId_pelicula() + "'>Editar</button>");
                       out.print("<input type='hidden' name='accion' value='editarPelicula1'/> </form>");
                       out.print("</td></tr>");
                    }
                %>

            </table>
                
            <div id="añadirPeli">
                <h2>Añadir pelicula</h2>
                <form action='./Controlador' method='POST'>
                    <label for="nombrePeli">Nombre:</label><input type="text" required name="nombrePeli" id="nombrePeli"/>
                    <label for="costesPeli">Costes:</label><input type="number" required name="costesPeli" id="costesPeli"/>
                    <label for="directorPeli">Director:</label>
                    <select name="directorPeli" id="directorPeli">
                    <%
                        for (Director director : lista2) {
                            out.print("<option value='" + director.getId_director() + "'>" + director.getId_director() + "</option>");  
                         }
                    %>
                    <input type='hidden' name='accion' value='insertPelicula'/>
                    <input type="submit" value="Añadir"/>
                    </select>          
                </form>
            </div>
                    
            <div id="verPelis">
                <h2>Buscar peliculas de un director</h2>
                <form action='./Controlador' method='POST'>
                    <label for="verPelis">Id director:</label>
                    <select name="verPelis" id="verPelis">
                    <%
                        for (Director director : lista2) {
                            out.print("<option value='" + director.getId_director() + "'>" + director.getId_director() + "</option>");  
                         }
                    %> 
                    <input type='hidden' name='accion' value='verPeliculasDirector'/>
                    <input type="submit" value="Buscar"/>
                    </select>          
                </form>
            </div>
        </div>
        
        <div id="directores">
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
                       out.print("<form method='POST' action='./Controlador'><button type='submit' name='borraDirector' value='" + director.getId_director() + "' >Borrar</button>");
                       out.print("<input type='hidden' name='accion' value='borrarDirector'/> </form>");
                       out.print("</td><td>");
                       out.print("<form method='POST' action='./Controlador'><button type='submit' name='editDirectores' value='" + director.getId_director() + "'>Editar</button>");
                       out.print("<input type='hidden' name='accion' value='editarDirector1'/> </form>");
                       out.print("</td></tr>");  
                    }

                %>

            </table>

            <div id="añadirDirector">
                <h2>Añadir Director</h2>
                <form action='./Controlador' method='POST'>
                    <label for="nombreDirect">Nombre:</label><input type="text" required name="nombreDirect" id="nombreDirect"/>
                    <label for="apeDirect">Apellidos:</label><input type="text" required name="apeDirect" id="apeDirect"/>
                    <label for="edadDirect">Edad:</label><input type="number" required name="edadDirect" id="edadDirect"/>
                    <input type='hidden' name='accion' value='insertDirector'/>
                    <input type="submit" value="Añadir"/>
                </form>
            </div>
        </div>
        
            
        
 
    </body>
</html>
