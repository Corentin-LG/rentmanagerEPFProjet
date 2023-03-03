package com.epf.rentmanager.ui.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/users")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/list.jsp").forward(request, response);
//        ClientService clientService = ClientService.getInstance();
//        int nbClients = clientService.getCount();
//        request.setAttribute("nbClients", nbClients);
//
//        VehicleService vehicleService = VehicleService.getInstance();
//        int nbVehicle = vehicleService.getCount();
//        request.setAttribute("nbVehicle", nbVehicle);


    }
}
