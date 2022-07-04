/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import ejb.DatabaseSessionRemote;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 37409
 */
@WebServlet(name = "TestServlet", urlPatterns = {"/"})
public class TestServlet extends HttpServlet {

    @EJB
    private DatabaseSessionRemote databaseSession;

    //This annotation INJECTS the TestEJBRemove object from EJB 
    //into this attribute
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, 
	HttpServletResponse response) throws ServletException,
	IOException {
	         response.setContentType("text/html;charset=UTF-8");
//                 ArrayList<Map<String, String>> coffeeList = databaseSession.selectAll();
//                 PrintWriter out = response.getWriter();
//                 out.println("<html>");
//                 out.println("<head>");
//                 out.println("<title>Servlet TestServlet</title>");
//                 out.println("</head>");
//                 out.println("<body>");
//                 for (int i=0; i<coffeeList.size(); i++){
//                    out.println(coffeeList.get(i).get("id"));
//                    out.println(coffeeList.get(i).get("u_name"));
//                    out.println(coffeeList.get(i).get("c_name"));
//                    out.println(coffeeList.get(i).get("sugar"));
//                    out.println(coffeeList.get(i).get("temperature"));
//
//                }
//                 out.println(mySession.getResult());
//                 out.println("</body>");
//                 out.println("</html>");
            String action = request.getServletPath();
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
            case "/search":
                searchCoffee(request, response);
                break;
            case "/searchByName":
                searchCoffeeByName(request, response);
                break;
            default:
                listCoffee(request, response);
                break;
            }
        }

    private void listCoffee(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        ArrayList<Map<String, String>> coffeeList = databaseSession.selectAll();
        request.setAttribute("coffeeList", coffeeList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("CoffeeList.jsp");
        dispatcher.forward(request, response);
    }
    
    private void deleteCoffee(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        String id = request.getParameter("id");
        
        databaseSession.delete(id);
        response.sendRedirect("list");
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("CoffeeForm.jsp");
        dispatcher.forward(request, response);
    }

    private void insertCoffee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String u_name = request.getParameter("u_name");
        String c_name = request.getParameter("c_name");
        String sugar = request.getParameter("sugar");
        String temperature = request.getParameter("temperature");
 
        databaseSession.insert(u_name, c_name, sugar, temperature);
        response.sendRedirect("list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Map<String, String> exCoffee = databaseSession.selectById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("CoffeeForm.jsp");
        request.setAttribute("coffee", exCoffee);
        dispatcher.forward(request, response);
    }

    private void updateCoffee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String u_name = request.getParameter("u_name");
        String c_name = request.getParameter("c_name");
        String sugar = request.getParameter("sugar");
        String temperature = request.getParameter("temperature");
 
        databaseSession.update(id, u_name, c_name, sugar, temperature);
        response.sendRedirect("list");
    }

    private void searchCoffee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("search_id");
        Map<String, String> exCoffee = databaseSession.selectById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("CoffeeForm.jsp");
        request.setAttribute("coffee", exCoffee);
        dispatcher.forward(request, response);
    }
    
    private void searchCoffeeByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchName = request.getParameter("search_name");
        ArrayList<Map<String, String>> coffeeList = databaseSession.selectByName(searchName);
        request.setAttribute("coffeeList", coffeeList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("CoffeeList.jsp");
        dispatcher.forward(request, response);
    }
}

