/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integracion.java.servel.servlets.maintainers.census;

import com.google.gson.Gson;
import entities.Census;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import services.ApiDPA;
import services.maintainers.Census_Service;

/**
 *
 * @author Joe-Xidu
 */
@WebServlet(name = "CensusAllSave", urlPatterns = {"/mantenedores/padron"})
public class CensusAllSave extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_42724/Census/Census.wsdl")
    private Census_Service censusService = new Census_Service();
    private final services.maintainers.Census port = this.censusService.getCensusPort();
    private ApiDPA apiDPA;

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
        HttpSession session = request.getSession();
        ArrayList<Census> list = new Gson().fromJson( this.port.all(), java.util.ArrayList.class );
        Map<String, String> regions;
        try {
            apiDPA = new ApiDPA();
            regions = apiDPA.getRegions();
        } catch (Exception e) {
            System.out.println(e);
            regions = new HashMap<>();
        }
        session.setAttribute("regions", regions);
        session.setAttribute("list", list);
        view("/maintainers/census/all.jsp", request, response);
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
        String json = "{\"response\":0}";
        try {
            String id = request.getParameter("id");
            String table = request.getParameter("table");
            String room = request.getParameter("room");
            String local = request.getParameter("local");
            String district = request.getParameter("district");
            String status = request.getParameter("status");
            Census census = new Census(id, table, room, local, district, status);
            if (id.equalsIgnoreCase("0")) {
                if (this.port.insert(new Gson().toJson( census )))
                    json = "{\"response\":1}";
            } else {
                if (this.port.update(new Gson().toJson( census )))
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
