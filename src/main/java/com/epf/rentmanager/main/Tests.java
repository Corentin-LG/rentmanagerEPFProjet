package com.epf.rentmanager.main;
//le test UI

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

public class Tests {
    public static void main(String[] args) {
        /*Client clientTesteur = new Client(1, "LE GOFF", "Corentin", "corentinlegoff@epfedu.fr", LocalDate.of(2000, 2, 11));
        System.out.println(clientTesteur.toString());
        System.out.println(clientTesteur.getForName());
        Client clientTesteur2 = new Client(1, "LE GOFF", "Corentin", "corentinlegoff@epfedu.fr", LocalDate.of(2000, 2, 11));
        System.out.println(clientTesteur.hashCode());
        System.out.println(clientTesteur2.hashCode());*/




        try {
            System.out.println(ClientService.getInstance().findAll());
            System.out.println(VehicleService.getInstance().findAll());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
