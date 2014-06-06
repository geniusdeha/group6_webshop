/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package user;

import beans.WebshopIO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HP
 */
public class BuyServlet extends HttpServlet {

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
        doPost(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        
        String customerID = request.getParameter("customerID");
        String quantitycomputer = request.getParameter("quantitycomputer");
        String quantitypizza = request.getParameter("quantitypizza");
        String quantitysalad = request.getParameter("quantitysalad");
        
        String pricecomputer = request.getParameter("pricecomputer");
        String pricepizza = request.getParameter("pricepizza");
        String pricesalad = request.getParameter("pricesalad");
        
        String orderReceipt = quantitycomputer + " computer, " +
                              quantitypizza + " pizza, " + 
                              quantitysalad + " salad";
        
        String message = null;
        boolean problem = false;
        
        int totalPrice = Integer.parseInt(pricecomputer) * Integer.parseInt(quantitycomputer) +
                         Integer.parseInt(pricepizza) * Integer.parseInt(quantitypizza) +
                         Integer.parseInt(pricesalad) * Integer.parseInt(quantitysalad);
        
        int productCount = Integer.parseInt(quantitycomputer) +
                           Integer.parseInt(quantitypizza) +
                           Integer.parseInt(quantitysalad);
              
        if(productCount==0){
            problem = true;
            message = "You did not choose any products to buy!";
            ServletContext sc = getServletContext();
            sc.setAttribute("message", message);
            //error
            RequestDispatcher error = getServletContext().getRequestDispatcher("/error.jsp");
            error.forward(request, response);
        }
        
        if(Integer.parseInt(quantitypizza)>0){
            if(!WebshopIO.buyPizza(Integer.parseInt(quantitypizza))){
                problem = true;
                message = "Not enough components to buy pizza.";
                ServletContext sc = getServletContext();
                sc.setAttribute("message", message);
                //error
                RequestDispatcher error = getServletContext().getRequestDispatcher("/error.jsp");
                error.forward(request, response);
            }
        }
        
        if(Integer.parseInt(quantitysalad)>0){
            if(!WebshopIO.buySalad(Integer.parseInt(quantitysalad))){
                problem = true;
                message = "Not enough components to buy salad.";
                ServletContext sc = getServletContext();
                sc.setAttribute("message", message);
                //error
                RequestDispatcher error = getServletContext().getRequestDispatcher("/error.jsp");
                error.forward(request, response);
            }
        }
        
        if(Integer.parseInt(quantitycomputer)>0){ 
            if(!WebshopIO.buyComputer(Integer.parseInt(quantitycomputer))){
                problem = true;
                message = "Not enough components to buy computer.";
                ServletContext sc = getServletContext();
                sc.setAttribute("message", message);
                //error
                RequestDispatcher error = getServletContext().getRequestDispatcher("/error.jsp");
                error.forward(request, response);
            }
        }
        
        if(!problem){
        
            int orderNumber = WebshopIO.createOrder(customerID, orderReceipt, ("" + totalPrice));
            message = "Your order of " + orderReceipt + " is registered with number " + orderNumber + ".\n"
                + "You can check it under Order History in your Profile.";
        
            ServletContext sc = getServletContext();
            sc.setAttribute("message", message);
            sc.setAttribute("customerID", customerID);
            //success
            RequestDispatcher success = getServletContext().getRequestDispatcher("/receipt.jsp");

            success.forward(request, response);
        }
        
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
