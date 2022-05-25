package qunlysanpham.controller;

import qunlysanpham.model.Customer;
import qunlysanpham.service.CustomerDAO;
import qunlysanpham.service.ICustomerDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customers")
public class CustomerServlet extends HttpServlet {
    ICustomerDAO customerDAO = new CustomerDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("huhi");
        if(action == null) {
            action = "";
        }
        switch (action){
            case "create":
                showCreateForm(request,response);
            default:
                showList(request,response);
        }
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

    }
}
