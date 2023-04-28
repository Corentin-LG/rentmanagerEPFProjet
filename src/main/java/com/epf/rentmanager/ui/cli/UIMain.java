package com.epf.rentmanager.ui.cli;

import com.epf.rentmanager.utils.IOUtils;


public class UIMain {
    private static boolean fin = false;

    public static void main(String[] args) {
        System.out.println("Bonjour");
        while (!fin) {
            afficherMenu();
        }
    }

    public static void afficherMenu() {
        IOUtils.print("Que voulez-vous faire ?");
        IOUtils.print(" [1] Afficher les enregistrements \n [2] Créer des enregistrements \n [3] Quitter le programme");

        int choix = IOUtils.readInt("Entrez votre choix : ");
        switch (choix) {
            case 1 -> afficherListOptions();
            case 2 -> afficherCreateOptions();
            case 3 -> {
                IOUtils.print("Au revoir !");
                fin = true;
            }
            default -> IOUtils.print("Le choix [" + choix + "] n'est pas reconnu");
        }
    }

    public static void afficherListOptions() {
        IOUtils.print(" [1] Lister les clients \n [2] Lister les véhicules \n [3] Lister les réservations \n [4] Quitter le programme");

        int choix = IOUtils.readInt("Entrez votre choix : ");
        switch (choix) {
            case 1 -> UIClient.listClients();
            case 2 -> UIVehicle.listVehicles();
            case 3 -> UIReservation.listReservations();
            case 4 -> {
                IOUtils.print("Au revoir !");
                fin = true;
            }
            default -> IOUtils.print("Le choix [" + choix + "] n'est pas reconnu");
        }
    }

    public static void afficherCreateOptions() {
        IOUtils.print(" [1] Créer un client \n [2] Créer un véhicule \n [3] Créer une réservation \n [4] Quitter le programme");
        int choix = IOUtils.readInt("Entrez votre choix : ");
        switch (choix) {
            case 1 -> UIClient.createClient();
            case 2 -> UIVehicle.createVehicle();
            case 3 -> UIReservation.createReservation();
            case 4 -> {
                IOUtils.print("Au revoir !");
                fin = true;
            }
            default -> IOUtils.print("Le choix [" + choix + "] n'est pas reconnu");
        }
    }
}
