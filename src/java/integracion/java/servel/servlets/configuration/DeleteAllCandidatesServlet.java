/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integracion.java.servel.servlets.configuration;

import com.google.gson.Gson;
import entities.Process;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import services.maintainers.CandidatesProcess;
import services.maintainers.CandidatesProcess_Service;
import services.maintainers.Processes_Service;

/**
 *
 * @author Joe
 */
@WebServlet(name = "DeleteAllCandidatesServlet", urlPatterns = {"/configuracion/candidatos/borrar"})
public class DeleteAllCandidatesServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_42724/CandidatesProcess/CandidatesProcess.wsdl")
    private CandidatesProcess_Service candidatesProcessService = new CandidatesProcess_Service();
    private final CandidatesProcess portCandidatesProcess = this.candidatesProcessService.getCandidatesProcessPort();

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_42724/Processes/Processes.wsdl")
    private Processes_Service processesService = new Processes_Service();
    private final services.maintainers.Processes portProcesses = this.processesService.getProcessesPort();
    
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
        response.sendRedirect("/integracion.java/configuracion");
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
            String id_process = request.getParameter("id");
            Process process = new Gson().fromJson( this.portProcesses.get(id_process), entities.Process.class );
            if (Integer.parseInt(process.getStatus()) < 2) {
                if (this.portCandidatesProcess.deleteByProcess(id_process))
                    json = "{\"response\":1}";
                else
                    json = "{\"response\":0, \"msg\":\"No hay candidatos que borrar.\"}";
            } else
                json = "{\"response\":0, \"msg\":\"No se pueden eliminar candidatos de un proceso en curso o terminado.\"}";
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

