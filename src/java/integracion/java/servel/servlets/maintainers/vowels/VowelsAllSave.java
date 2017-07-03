/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integracion.java.servel.servlets.maintainers.vowels;

import com.google.gson.Gson;
import entities.Census;
import entities.Vowel;
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
import services.maintainers.Vowels_Service;
import services.Validation_Service;
import services.maintainers.Census_Service;

/**
 *
 * @author Joe-Xidu
 */
@WebServlet(name = "VocalsAllSave", urlPatterns = {"/mantenedores/vocales"})
public class VowelsAllSave extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_42724/Vowels/Vowels.wsdl")
    private Vowels_Service vowelsService = new Vowels_Service();
    private services.maintainers.Vowels port = this.vowelsService.getVowelsPort();
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_42724/Census/Census.wsdl")
    private Census_Service censusService = new Census_Service();
    private services.maintainers.Census portCensus = this.censusService.getCensusPort();
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_42724/validation/validation.wsdl")
    private Validation_Service validation;

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
        ArrayList<Vowel> list = new Gson().fromJson( this.port.all(), java.util.ArrayList.class );
        ArrayList<Census> census = new Gson().fromJson( this.portCensus.all(), java.util.ArrayList.class );
        session.setAttribute("list", list);
        session.setAttribute("census_list", census);
        view("/maintainers/vowels/all.jsp", request, response);
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
        services.Validation val = this.validation.getValidationPort();
        String json = "{\"response\":0}";
        try {
            String id = request.getParameter("id");
            String rut = request.getParameter("rut");
            String dv = request.getParameter("dv");
            String name = request.getParameter("name");
            String id_census = request.getParameter("id_census");
            String status = request.getParameter("status");
            
            if (val.validaterut(rut + '-' + dv)) {
                Vowel vocal = new Vowel(id, rut, dv, name, id_census, status);
                if (id.equalsIgnoreCase("0")) {
                    if (this.port.insert(new Gson().toJson( vocal )))
                        json = "{\"response\":1}";
                } else {
                    if (this.port.update(new Gson().toJson( vocal )))
                        json = "{\"response\":1}";
                }
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
