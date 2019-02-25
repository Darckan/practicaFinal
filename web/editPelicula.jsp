<%-- 
    Document   : editPelicula
    Created on : 25-feb-2019, 17:22:56
    Author     : darck
--%>
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
        <title>JSP Page</title>
    </head>
    <body>
        <div>
        <form action='./Controlador' method='POST'>
            <%
                Object id = session.getAttribute("editPeliculas");
                ArrayList<Director> lista2 = DirectorDAO.consultarDirectores();
                ArrayList<Pelicula> lista = PeliculaDAO.consultarPeliculas();
                
                for (Pelicula pelicula : lista) {
                    if(pelicula.getId_pelidula() == Integer.parseInt(id.toString())){
                        out.print("<label for='nombreEditPeli'>Nombre</label><input type='text' required name='nombreEditPeli' id='nombreEditPeli' value='" + pelicula.getNombre() + "'/>");            
                        out.print("<label for='costesEditPeli'>Costes</label><input type='number' required name='costesEditPeli' id='costesEditPeli' value='" + pelicula.getCostes() + "'/>");
                        out.print("<label for='directorEditPeli'>Director</label>");
                        out.print("<select name='directorEditPeli' id='directorEditPeli'>");
                        for (Director director : lista2) {
                            if(director.getId_director() == pelicula.getId_director()){
                                out.print("<option value='" + director.getId_director() + "' selected>" + director.getId_director() + "</option>");
                            }else
                                out.print("<option value='" + director.getId_director() + "'>" + director.getId_director() + "</option>");  
                        }
                    }
                }
                
            %>
                <input type='hidden' name='editPeliculas' value='${sessionScope['editPeliculas']}'/>
                <input type='hidden' name='accion' value='editarPelicula2'/>
                <input type="submit" value="Editar"/>
                </select>          
            </form>
        </div>
    </body>
</html>
