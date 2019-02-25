<%-- 
    Document   : PeliculasDeDirector
    Created on : 23-feb-2019, 17:57:46
    Author     : darck
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Pelicula"%>
<%@page import="modelo.PeliculaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Peliculas de un director</title>
    </head>
    <body>
        <h1>Peliculas del director con id ${sessionScope['directorId']}</h1>

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
                HttpSession sesion = request.getSession(true);
                Object id = session.getAttribute("directorId");
                // Lista ordenada por nombre 
                ArrayList<Pelicula> lista = PeliculaDAO.consultarPeliculasDeDirector(Integer.parseInt(id.toString()));
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
    </body>
</html>
