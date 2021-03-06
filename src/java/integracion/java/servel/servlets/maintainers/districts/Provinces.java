/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integracion.java.servel.servlets.maintainers.districts;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.ApiDPA;

/**
 *
 * @author Joe-Xidu
 */
@WebServlet(name = "Provinces", urlPatterns = {"/mantenedores/provinces"})
public class Provinces extends HttpServlet {
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
        Map<String, String> provinces;
        String json = "{\"provinces\":[";
        try {
            String region = request.getParameter("region");
            apiDPA = new ApiDPA();
            provinces = apiDPA.getProvinces(region);
            int size = provinces.size();
            for (Map.Entry<String, String> province : provinces.entrySet()) {
                if (--size == 0)
                    json += "{\"id\": \"" + province.getKey() + "\", \"text\": \"" + province.getValue()+ "\"}";
                else
                    json += "{\"id\": \"" + province.getKey() + "\", \"text\": \"" + province.getValue() + "\"},";
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        json += "]}";
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
