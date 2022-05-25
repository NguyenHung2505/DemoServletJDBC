package qunlysanpham.controller;

import qunlysanpham.model.Customer;
import qunlysanpham.service.CustomerDAO;
import qunlysanpham.service.ICustomerDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customers")
public class CustomerServlet extends HttpServlet {
    CustomerDAO customerDAO = new CustomerDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("huhi");
        if(action == null) {
            action = "";
        }
        switch (action){
            case "create":
                showCreateForm(request,response);
            case "edit":
                showEditForm(request,response);
            case "delete":
                showdeleteForm(request,response);
                break;
            default:
                showList(request,response);
        }

    }

    private void showdeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            customerDAO.delete(id);
        } catch (SQLException e) {
           throw new RuntimeException();
        }
        List<Customer> customers = customerDAO.findAll();
        request.setAttribute("dskh" , customers);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/customer/list.jsp");
        requestDispatcher.forward(request,response);

    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("customer/edit.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = customerDAO.findById(id);
        request.setAttribute("cansua",customer);
        requestDispatcher.forward(request,response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/customer/create.jsp");
        requestDispatcher.forward(request,response);
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/customer/list.jsp");
        List<Customer> customers = customerDAO.findAll();
        request.setAttribute("dskh" , customers);
        requestDispatcher.forward(request,response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("huhi");
        if(action == null) {
            action = "";
        }
        switch (action){
            case "create":
                try {
                    save(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "edit":
                try {
                    edit(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            default:
                showList(request,response);
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        Customer customer=new Customer(id,name,age);
        customerDAO.update(customer);
        response.sendRedirect("/customers");
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        customerDAO.add(new Customer(id,name,age));
        response.sendRedirect("/customers");


    }
}
