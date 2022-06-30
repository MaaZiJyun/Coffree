/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.caffree.ejb;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author www.codejava.net
 */
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CoffeeDAO coffeeDAO;
 
    @Override
    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        coffeeDAO = new CoffeeDAO(jdbcURL, jdbcUsername, jdbcPassword);
 
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
 
        try {
            switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertCoffee(request, response);
                break;
            case "/delete":
                deleteCoffee(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateCoffee(request, response);
                break;
            default:
                listCoffee(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
 
    private void listCoffee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Coffee> listCoffee = coffeeDAO.listAllCoffees();
        request.setAttribute("listcoffee", listCoffee);
        RequestDispatcher dispatcher = request.getRequestDispatcher("CoffeeList.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("CoffeeForm.jsp");
        dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Coffee existingCoffee = coffeeDAO.getCoffee(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("CoffeeForm.jsp");
        request.setAttribute("coffee", existingCoffee);
        dispatcher.forward(request, response);
 
    }
 
    private void insertCoffee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String u_name = request.getParameter("u_name");
        String c_name = request.getParameter("c_name");
        int sugar = Integer.parseInt(request.getParameter("sugar"));
        int temperature = Integer.parseInt(request.getParameter("temperature"));
 
        Coffee newCoffee = new Coffee(u_name, c_name, sugar, temperature);
        coffeeDAO.insertCoffee(newCoffee);
        response.sendRedirect("list");
    }
 
    private void updateCoffee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String u_name = request.getParameter("u_name");
        String c_name = request.getParameter("c_name");
        int sugar = Integer.parseInt(request.getParameter("sugar"));
        int temperature = Integer.parseInt(request.getParameter("temperature"));
 
        Coffee coffee = new Coffee(id, u_name, c_name, sugar, temperature);
        coffeeDAO.updateCoffee(coffee);
        response.sendRedirect("list");
    }
 
    private void deleteCoffee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Coffee coffee = new Coffee(id);
        coffeeDAO.deleteCoffee(coffee);
        response.sendRedirect("list");
 
    }
}
