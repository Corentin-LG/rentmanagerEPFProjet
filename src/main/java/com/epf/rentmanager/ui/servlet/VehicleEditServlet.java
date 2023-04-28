package com.epf.rentmanager.ui.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cars/edit")
public class VehicleEditServlet extends HttpServlet {
    @Autowired
    VehicleService vehicleService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long vehicleId = Long.parseLong(request.getParameter("id"));
        try {
            request.setAttribute("vehicle", this.vehicleService.findById(vehicleId));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        this.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/vehicles/edit.jsp")
                .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long vehicleId = Long.parseLong(request.getParameter("id"));
        Vehicle vehicle = new Vehicle(vehicleId, request.getParameter("constructeur"),
                request.getParameter("modele"),
                Short.parseShort(request.getParameter("places")));
        try {
            vehicleService.edit(vehicleId, vehicle);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/rentmanager/cars");
    }
}

