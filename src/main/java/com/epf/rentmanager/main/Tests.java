package com.epf.rentmanager.main;
//le test UI

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import java.util.Scanner;

public class Tests {
    public static void main(String[] args) {
        /*Client clientTesteur = new Client(1, "LE GOFF", "Corentin", "corentinlegoff@epfedu.fr", LocalDate.of(2000, 2, 11));
        System.out.println(clientTesteur.toString());
        System.out.println(clientTesteur.getForName());
        Client clientTesteur2 = new Client(1, "LE GOFF", "Corentin", "corentinlegoff@epfedu.fr", LocalDate.of(2000, 2, 11));
        System.out.println(clientTesteur.hashCode());
        System.out.println(clientTesteur2.hashCode());*/


        try {
            System.out.println(new ClientService(new ClientDao()).findAll());
            ClientDao cld1 = new ClientDao();
            ClientService clS1 = new ClientService(cld1);
            System.out.println(clS1.findAll());
//            System.out.println(VehicleService.getInstance().findAll());
//            Scanner scanner = new Scanner(System.in);
//            System.out.print("Entrez une id client : ");
//            long idEntree = (long) scanner.nextDouble();
//            System.out.println(ClientService.getInstance().findById(idEntree));
//
//            System.out.print("Entrez une id vehicule: ");
//            scanner.nextDouble();
//            System.out.println(VehicleService.getInstance().findById(idEntree));
//
//            System.out.println(ReservationService.getInstance().findAll());
//
//            System.out.print("Entrez une id client de resa: ");
//            scanner.nextDouble();
//            System.out.println(ReservationService.getInstance().findResaByClientId(idEntree));
//
//            System.out.print("Entrez une id vehicule de resa: ");
//            scanner.nextDouble();
//            System.out.println(ReservationService.getInstance().findResaByVehicleId(idEntree));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
