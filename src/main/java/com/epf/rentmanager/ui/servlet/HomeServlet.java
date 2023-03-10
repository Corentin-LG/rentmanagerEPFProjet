package com.epf.rentmanager.ui.servlet;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    /**
     *Mapper, Try(ressources)
     */
    private static final long serialVersionUID = 1L;
    private ClientService clientService = new ClientService(new ClientDao());
    private VehicleService vehicleService = new VehicleService(new VehicleDao());
    private ReservationService reservationService = new ReservationService(new ReservationDao());

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("nbClients", clientService.count());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        try {
            request.setAttribute("nbVoitures", vehicleService.count());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        try {
            request.setAttribute("nbReservations", reservationService.count());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
    }

}
