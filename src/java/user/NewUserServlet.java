/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package user;

import beans.Customer;
import beans.WebshopIO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author deha
 */
@WebServlet(name = "NewUserServlet", urlPatterns = {"/newuser"})
public class NewUserServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewUserServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewUserServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        processRequest(request, response);
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
        String customerName = request.getParameter("c_name");
        String customerSurname = request.getParameter("c_surname");
        String customerAddress = request.getParameter("address");
        PrintWriter out = response.getWriter();
        out.println("Creating new customer. Please wait...");
        try {
            // JAVA CODE
            WebshopIO.initConnection("jdbc:mysql://127.0.0.1:3306/webshop");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        Customer customer = WebshopIO.createCustomer(customerName, customerSurname, customerAddress);
        
        ServletContext sc = getServletContext();
        sc.setAttribute("customer", customer);
        // JAVA CODE
        RequestDispatcher disp = getServletContext().getRequestDispatcher("/profile.jsp");
        disp.forward(request, response);
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
