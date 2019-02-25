<%-- 
    Document   : PeliculasDeDirector
    Created on : 23-feb-2019, 17:57:46
    Author     : darck
--%>

<%@page import="modelo.DirectorDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Director"%>
<%@page import="modelo.Pelicula"%>
<%@page import="modelo.PeliculaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./css/style.css" rel="stylesheet" type="text/css">
        <title>Peliculas de un director</title>
    </head>
    <body>
        <div class="margen">
            <% 
                Object id = session.getAttribute("directorId");
                Director nombre = DirectorDAO.consultarDirector(Integer.parseInt(id.toString()));
                out.print("<h1>Peliculas del director " + nombre.getApellidos() + " " + nombre.getNombre() + " </h1>");
            %>

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
                    ArrayList<Pelicula> lista = PeliculaDAO.consultarPeliculasDeDirector(Integer.parseInt(id.toString()));
                    for (Pelicula pelicula : lista) {
                       out.print("<tr><td>");
                       out.print(pelicula.getId_pelicula());
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
            <button type="button" onclick="location.href = './index.jsp'" class="margen">Volver</button>
        </div>
    </body>
</html>
