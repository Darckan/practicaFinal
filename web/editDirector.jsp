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
        <link href="./css/style.css" rel="stylesheet" type="text/css">
        <title>Editar Director</title>
    </head>
    <body>
        <div class="margen">
            <form action='./Controlador' method='POST'>
                <%
                    Object id = session.getAttribute("editDirectores");
                    out.print(Integer.parseInt(id.toString()));
                    ArrayList<Director> lista = DirectorDAO.consultarDirectores();

                    for (Director director : lista) {
                        if(director.getId_director() == Integer.parseInt(id.toString())){
                            out.print("<label for='nombreEditDirector'>Nombre</label><input type='text' required name='nombreEditDirector' id='nombreEditDirector' value='" + director.getNombre() + "'/>");
                            out.print("<label for='apellidosEditDirector'>Apellidos</label><input type='text' required name='apellidosEditDirector' id='apellidosEditDirector' value='" + director.getApellidos()+ "'/>");   
                            out.print("<label for='edadEditDirector'>Edad</label><input type='number' required name='edadEditDirector' id='edadEditDirector' value='" + director.getEdad() + "'/>");
                        }
                    }

                %>
                <input type='hidden' name='editDirectores' value='${sessionScope['editDirectores']}'/>
                <input type='hidden' name='accion' value='editarDirector2'/>
                <input type="submit" value="Editar"/>
                </select>          
            </form>
            <button type="button" onclick="location.href = './index.jsp'" class="margen">Volver</button>
        </div>
    </body>
</html>
