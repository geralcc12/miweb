/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import controller.UsuarioJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Usuario;

/**
 *
 * @author USER
 */
@WebServlet(name = "loginServlet", urlPatterns = {"/loginServlet"})
public class loginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        String nombreUsuario = request.getParameter("Correo");
        String contrasena = request.getParameter("password");
        
        UsuarioJpaController usuControl = new UsuarioJpaController();
        
        for (Usuario Usuario : usuControl.findUsuarioEntities()) {
            
            if (Usuario.getUsuario().equals(nombreUsuario) && Usuario.getPassword().equals(contrasena)) {
                response.sendRedirect("index.jsp");
            }
            
            
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
