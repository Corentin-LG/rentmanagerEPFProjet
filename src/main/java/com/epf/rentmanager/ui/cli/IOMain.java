package com.epf.rentmanager.ui.cli;

import com.epf.rentmanager.utils.IOUtils;

public class IOMain {
	private static boolean fin = false;

	public static void main(String[] args) {
		System.out.println("Bonjour");
		while (!fin) {
			afficherMenu();
		}
	}

	public static void afficherMenu() {
		IOUtils.print("Que voulez-vous faire ?");
		IOUtils.print("""
		[1] Afficher les enregistrements
		[2] Créer des enregistrements
		[3] Quitter le programme""");

		int choix = IOUtils.readInt("Entrez votre choix : ");
		switch (choix) {
			case 1 -> afficherOptions();
			case 2 -> displayCreateOptions();
			case 3 -> {
				IOUtils.print("Au revoir !");
				fin = true;
			}
			default -> IOUtils.print("Le choix [" + choix + "] n'est pas reconnu");
		}
	}

	public static void afficherOptions() {
		IOUtils.print("""
		[1] Lister les clients
		[2] Lister les véhicules
		[3] Lister les réservations
		[4] Quitter le programme""");
		int choix = IOUtils.readInt("Entrez votre choix : ");
		switch (choix) {
			case 1 -> IOClient.listClients();
			case 2 -> IOVehicle.listVehicles();
			case 3 -> IOReservation.listReservations();
			case 4 -> {
				IOUtils.print("Au revoir !");
				fin = true;
			}
			default -> IOUtils.print("Le choix [" + choix + "] n'est pas reconnu");
		}
	}

	public static void displayCreateOptions() {
		IOUtils.print("""
		[1] Créer un client
		[2] Créer un véhicule
		[3] Créer une réservation
		[4] Quitter le programme""");
		int choix = IOUtils.readInt("Entrez votre choix : ");
		switch (choix) {
			case 1 -> IOClient.createClient();
			case 2 -> IOVehicle.createVehicle();
			case 3 -> IOReservation.createReservation();
			case 4 -> {
				IOUtils.print("Au revoir !");
				fin = true;
			}
			default -> IOUtils.print("Le choix [" + choix + "] n'est pas reconnu");
		}
	}
}
