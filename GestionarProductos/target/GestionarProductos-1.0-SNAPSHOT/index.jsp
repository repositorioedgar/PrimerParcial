<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.modelo.Productos"%>
<%
    if ( session.getAttribute("listapro") == null){
    ArrayList<Productos>lisaux = new ArrayList<Productos>();
    session.setAttribute("listapro", lisaux);
}
    ArrayList<Productos> lista = (ArrayList<Productos>) session.getAttribute("listapro");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p>PRIMER PARCIAL TEM-742
           NOMBRE:EDGAR CONDORI POLLOQUIRI
           CARNET: 6993294 </p>
        
        <h1>GESTIÓN DE PRODUCTOS</h1>
        
        <a href="MainServlet?op=nuevo">Nuevo registro</a>
        <table border="1">
            <tr>
                <th>id</th>
                <th>Descripcion</th>
                <th>Cantidad</th>
                <th>Precio</th>
                <th>Categoria</th> 
            </tr>
            <%
               if ( lista != null){ 
                   for(Productos item : lista){

            %>
            
            <tr>
                <td><%= item.getId()%></td>
                <td><%= item.getDescripcion()%></td>
                <td><%= item.getCantidad()%></td>
                <td><%= item.getPrecio()%></td>
                <td><%= item.getCategoria()%></td>
                <td> 
                    <a href="MainServlet?op=editar&id=<%= item.getId()%>">Editar </a>
                </td>
                <td> 
                    <a href="MainServlet?op=eliminar&id=<%= item.getId()%>"
                       onclick="return(confirm('esta seguro de eliminar??'))"
                       >Eliminar </a>
                </td>
            </tr>
            <%
                }
            }
            %>
        </table>
    </body>
</html>
