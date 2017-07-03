/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integracion.java.servel.servlets.configuration;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.Candidate;
import entities.CandidateProcess;
import entities.Process;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import services.maintainers.Candidates;
import services.maintainers.CandidatesProcess;
import services.maintainers.CandidatesProcess_Service;
import services.maintainers.Candidates_Service;
import services.maintainers.Parties;
import services.maintainers.Parties_Service;
import services.maintainers.Processes_Service;

/**
 *
 * @author Joe
 */
@WebServlet(name = "ResultsServlet", urlPatterns = {"/configuracion/resultados/proceso"})
public class ResultsServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_42724/CandidatesProcess/CandidatesProcess.wsdl")
    private CandidatesProcess_Service candidatesProcessService = new CandidatesProcess_Service();
    private final CandidatesProcess portCandidatesProcess = this.candidatesProcessService.getCandidatesProcessPort();

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_42724/Candidates/Candidates.wsdl")
    private Candidates_Service candidatesService = new Candidates_Service();
    private final Candidates portCandidates = this.candidatesService.getCandidatesPort();

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_42724/Processes/Processes.wsdl")
    private Processes_Service processesService = new Processes_Service();
    private final services.maintainers.Processes portProcesses = this.processesService.getProcessesPort();

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_42724/Parties/Parties.wsdl")
    private Parties_Service parties = new Parties_Service();
    private final services.maintainers.Parties portParties = this.parties.getPartiesPort();
    
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
        String id_process;
        try {
            id_process = request.getParameter("p");
        } catch (Exception e) {
            id_process = "0";
        }
        ArrayList<Candidate> candidates = new Gson().fromJson( portCandidates.all(), java.util.ArrayList.class );
        Process process = new Gson().fromJson( portProcesses.get(id_process), entities.Process.class );
        ArrayList<Parties> parties = new Gson().fromJson( portParties.all(), java.util.ArrayList.class );
        ArrayList<CandidateProcess> candidatesProcess = new Gson().fromJson( portCandidatesProcess.byProcess(id_process), new TypeToken<ArrayList<CandidateProcess>>(){}.getType() );
        int total = 0;
        for (CandidateProcess candidatesProces : candidatesProcess) {
            total += Integer.parseInt(candidatesProces.getVotes());
        }
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        session.setAttribute("df", df);
        session.setAttribute("total", total);
        session.setAttribute("process", process);
        session.setAttribute("candidates", candidates);
        session.setAttribute("parties", parties);
        session.setAttribute("candidatesProcess", candidatesProcess);
        view("/configuration/results.jsp", request, response);
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
            ArrayList<CandidateProcess> candidatesProcess = new Gson().fromJson( portCandidatesProcess.byProcess(id), new TypeToken<ArrayList<CandidateProcess>>(){}.getType() );
            entities.Process process = new Gson().fromJson( this.portProcesses.get(id), entities.Process.class );
            if (process.getStatus().equalsIgnoreCase("2")) {
                for (CandidateProcess candidatesProces : candidatesProcess) {
                    this.portCandidatesProcess.updateVotes(candidatesProces.getId());
                }
            }
            json = "{\"response\":1}";
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


