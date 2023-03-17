package com.epf.rentmanager.main;
//le test UI

import com.epf.rentmanager.config.AppConfiguration;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Tests {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        ClientService clientService = context.getBean(ClientService.class);
        VehicleService vehicleService = context.getBean(VehicleService.class);
        ReservationService reservationService = context.getBean(ReservationService.class);
        try {
            System.out.println(new ClientService(new ClientDao()).findAll());
            ClientDao cld1 = new ClientDao();
            ClientService clS1 = new ClientService(cld1);
            System.out.println(clS1.findAll());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
