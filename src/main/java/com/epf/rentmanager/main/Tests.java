package com.epf.rentmanager.main;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

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
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
