/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integracion.java.servel.servlets.maintainers.candidates;

import com.google.gson.Gson;
import entities.Candidate;
import entities.Part;
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
import services.Validation;
import services.Validation_Service;
import services.maintainers.Candidates;
import services.maintainers.Candidates_Service;
import services.maintainers.Parties;
import services.maintainers.Parties_Service;

/**
 *
 * @author Joe-Xidu
 */
@WebServlet(name = "CandidatesAllSave", urlPatterns = {"/mantenedores/candidatos"})
public class CandidatesAllSave extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_42724/Candidates/Candidates.wsdl")
    private Candidates_Service candidatesService = new Candidates_Service();
    private final Candidates portCandidates = this.candidatesService.getCandidatesPort();
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_42724/Parties/Parties.wsdl")
    private Parties_Service partiesService = new Parties_Service();
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
        Parties portParties = this.partiesService.getPartiesPort();
        HttpSession session = request.getSession();
        ArrayList<Candidate> list = new Gson().fromJson( this.portCandidates.all(), java.util.ArrayList.class );
        ArrayList<Part> parties = new Gson().fromJson( portParties.all(), java.util.ArrayList.class );
        session.setAttribute("list", list);
        session.setAttribute("parties", parties);
        view("/maintainers/candidates/all.jsp", request, response);
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
        Validation val = this.validation.getValidationPort();
        String json = "{\"response\":0}";
        try {
            String id = request.getParameter("id");
            String rut = request.getParameter("rut");
            String dv = request.getParameter("dv");
            String name = request.getParameter("name");
            String id_part = request.getParameter("id_part");
            String status = request.getParameter("status");
            
            if (val.validaterut(rut + '-' + dv)) {
                Candidate candidate = new Candidate(id, rut, dv, name, id_part, status);
                if (id.equalsIgnoreCase("0")) {
                    if (this.portCandidates.insert(new Gson().toJson( candidate )))
                        json = "{\"response\":1}";
                } else {
                    if (this.portCandidates.update(new Gson().toJson( candidate )))
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
