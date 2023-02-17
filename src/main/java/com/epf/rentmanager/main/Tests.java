package com.epf.rentmanager.main;

import com.epf.rentmanager.model.Client;

import java.time.LocalDate;

public class Tests {
    public static void main(String[] args){
        Client clientTesteur = new Client(1,"LE GOFF", "Corentin", "corentinlegoff@epfedu.fr", LocalDate.of(2000, 2, 11));
        System.out.println(clientTesteur.toString());
        System.out.println(clientTesteur.getForName());
        Client clientTesteur2 = new Client(1,"LE GOFF", "Corentin", "corentinlegoff@epfedu.fr", LocalDate.of(2000, 2, 11));
        System.out.println(clientTesteur.hashCode());
        System.out.println(clientTesteur2.hashCode());
    }
}
