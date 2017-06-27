/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integracion.java.servel.servlets.maintainers.users;

import com.google.gson.Gson;
import entities.User;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import services.Maintainers_Service;

/**
 *
 * @author jose.becerra
 */
@WebServlet(name = "UsersAllSave", urlPatterns = {"/mantenedores/usuarios"})
public class UsersAllSave extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/maintainers/maintainers.wsdl")
    private Maintainers_Service maintainers;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        services.Maintainers port = this.maintainers.getMaintainersPort();
        HttpSession session = request.getSession();
        ArrayList<User> list = new Gson().fromJson( port.all("users"), java.util.ArrayList.class );
        session.setAttribute("list", list);
        view("/maintainers/users/all.jsp", request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        services.Maintainers port = this.maintainers.getMaintainersPort();
        String json = "{\"response\":0}";
        try {
            String id = request.getParameter("id");
            String rut = request.getParameter("rut");
            String dv = request.getParameter("dv");
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String status = request.getParameter("status");
            String profile = request.getParameter("profile");
            User user = new User(id, login, password, profile, name, rut, dv, status);
            if (id.equalsIgnoreCase("0")) {
                if (port.insert("users", new Gson().toJson( user )))
                    json = "{\"response\":1}";
            } else {
                if (port.update("users", new Gson().toJson( user )))
                    json = "{\"response\":1}";
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        session.setAttribute("json", json);
        view("/include/json.jsp", request, response);
    }
    
    private void view(String view, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher r;
        r = request.getRequestDispatcher(view);
        r.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
