package com.emergentes.controlador;

import com.emergentes.modelo.Productos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {
 

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String op = request.getParameter("op");
        Productos objpro = new Productos();
        int id, pos;
        HttpSession ses = request.getSession();
        ArrayList<Productos> lista = (ArrayList<Productos>)ses.getAttribute("listapro");
        
        switch (op){
            case "nuevo":
                //enviar un objeto vacio a editar
                request.setAttribute("miobjpro", objpro);
                request.getRequestDispatcher("editar.jsp").forward(request,response);
                break;
            case "editar":
                //enviar un objeto a editar pero con contenido
                id = Integer.parseInt(request.getParameter("id"));
                pos = buscarPorIndice(request,id);
                objpro = lista.get(pos);
                request.setAttribute("miobjpro", objpro);
                request.getRequestDispatcher("editar.jsp").forward(request,response);
                break;
            case "eliminar":
                //eliminar el registro de la coleccion segun el id
                id = Integer.parseInt(request.getParameter("id"));
                pos = buscarPorIndice(request,id);
                if (pos >=0){
                    lista.remove(pos);   
                }
                request.setAttribute("listapro",lista);
                response.sendRedirect("index.jsp");
                break;
            default:
                
            }
         
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession ses = request.getSession();
        ArrayList<Productos> lista = (ArrayList<Productos>)ses.getAttribute("listapro");
        Productos objpro = new Productos();
        objpro.setId(Integer.parseInt(request.getParameter("id")));
        objpro.setDescripcion(request.getParameter("descripcion"));
        objpro.setCantidad(Integer.parseInt(request.getParameter("canatidad")));
        objpro.setPrecio(Integer.parseInt(request.getParameter("precio")));
        objpro.setCategoria(request.getParameter("categoria"));
        
         if(id == 0){
            int idNuevo;
            idNuevo = obtenerId(request);
            objpro.setId(idNuevo);
            lista.add(objpro);
        }
        else{
            int pos = buscarPorIndice(request,id);
            lista.set(pos,objpro);
        } 
        ses.setAttribute("listapro", lista);
        response.sendRedirect("index.jsp");
    }
 
    @Override
    public String getServletInfo() {
        return "Short description";
    } 
    
    public int buscarPorIndice(HttpServletRequest request, int id){
        HttpSession ses = request.getSession();
        ArrayList<Productos> lista = (ArrayList<Productos>) ses.getAttribute("listapro");
        int pos = -1;
        if (lista != null) {
            for(Productos ele : lista ) {
                ++pos;
                if(ele.getId() == id) {
                    break;
                }
            }
        }
        return pos;
    }
    
    public int obtenerId(HttpServletRequest request){
       HttpSession ses = request.getSession(); 
       ArrayList<Productos> lista = (ArrayList<Productos>)ses.getAttribute("listapro"); 
       int idn = 0;
       for (Productos ele : lista) {
           idn = ele.getId();
       }
       return idn + 1;
    }

}
