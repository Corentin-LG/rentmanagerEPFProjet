package com.epf.rentmanager.ui.cli;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.utils.IOUtils;

import java.time.LocalDate;
import java.util.List;

public class IOReservation {
	public static void listReservations() {
		try {
			for (Reservation reservation : new ReservationService(new ReservationDao()).findAll()) {
				IOUtils.print(reservation.toString());
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	public static void createReservation() {
		Reservation reservation = new Reservation();
		IOUtils.print("Création d'une réservation");
		try {
			reservation.setClient(IOClient.selectClient());
			reservation.setVehicle(IOVehicle.selectVehicle());
			reservation.setDebut(IOUtils.readDate("Entrez une date de début de réservation :", true));
			LocalDate dateFin;
			do {
				dateFin = IOUtils.readDate("Entrez une date de fin de réservation : ", true);
			} while (dateFin.isBefore(reservation.getDebut()));
			reservation.setFin(dateFin);
			long ID = new ReservationService(new ReservationDao()).create(reservation);
			IOUtils.print("La réservation [" + ID + "] a été créé");
		} catch (ServiceException e) {
			e.printStackTrace();
			IOUtils.print("La réservation n'a pas pu être créée");
		}
	}

	public static Reservation selectReservation() throws ServiceException {
		List<Reservation> reservationList = new ReservationService(new ReservationDao()).findAll();
		int index;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		do {
			for (Reservation reservation : reservationList) {
				long id = reservation.getId();
				IOUtils.print(" [" + id + "] " + reservation);
				min = Integer.min(min, (int) id);
				max = Integer.max(max, (int) id);
			}
			index = IOUtils.readInt("Entrez un indice :");
		} while (index < min || index >= max);
		return reservationList.get(index - 1);
	}
}
