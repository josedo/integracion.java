/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integracion.java.servel.servlets.maintainers.processes;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import entities.Process;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import services.maintainers.Processes_Service;

/**
 *
 * @author Joe-Xidu
 */
@WebServlet(name = "ProcessesAllSave", urlPatterns = {"/mantenedores/procesos"})
public class ProcessesAllSave extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_42724/Processes/Processes.wsdl")
    private Processes_Service processesService = new Processes_Service();
    private final services.maintainers.Processes port = this.processesService.getProcessesPort();

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
        ArrayList<Process> list = new Gson().fromJson( this.port.all(), java.util.ArrayList.class );
        session.setAttribute("list", list);
        view("/maintainers/processes/all.jsp", request, response);
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
            String name = request.getParameter("name");
            String status = request.getParameter("status");
            Process process = new Process(id, name, status);
            if (id.equalsIgnoreCase("0")) {
                if (this.port.insert(new Gson().toJson( process )))
                    json = "{\"response\":1}";
            } else {
                if (this.port.update(new Gson().toJson( process )))
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
