package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationDao reservationDao;

    public ReservationService(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    public Reservation findById(long id) throws ServiceException {
        try {
            return reservationDao.findById(id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }

    public long create(Reservation reservation) throws ServiceException {
        try {
            LocalDate beginDate = reservation.getDebut();
            LocalDate endDate = reservation.getFin();
            Period diff = Period.between(beginDate, endDate);

            if (diff.getDays() > 7 || diff.getMonths() > 0 || diff.getYears() > 0) {
                throw new ServiceException("Une voiture ne peut pas être louée plus de 7 jours d'affilés par un même Client");
            }

            return reservationDao.create(reservation);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }

    public List<Reservation> findResaByClientId(long id) throws ServiceException {
        try {
            List<Reservation> reservation = new ArrayList<Reservation>();
            reservation = reservationDao.findResaByClientId(id);
            for (Reservation r : reservation) {
                r.setVehicle(reservationDao.getVehicleDao().findById(r.getVehicle().getId()));
                r.setClient(reservationDao.getClientDao().findById(r.getClient().getId()));
            }
            return reservation;
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public List<Reservation> findResaByVehicleId(long id) throws ServiceException {
        try {
            List<Reservation> reservation = new ArrayList<Reservation>();
            reservation = reservationDao.findResaByVehicleId(id);
            for (Reservation r : reservation) {
                r.setVehicle(reservationDao.getVehicleDao().findById(r.getVehicle().getId()));
                r.setClient(reservationDao.getClientDao().findById(r.getClient().getId()));
            }
            return reservation;
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public List<Vehicle> findAllVehiclesPerClientId(long clientId) throws ServiceException {
        try {
            return reservationDao.findAllVehiclesPerClientId(clientId);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }

    public List<Reservation> findAll() throws ServiceException {
        try {
            List<Reservation> reservation = new ArrayList<Reservation>();
            reservation = reservationDao.findAll();
            for (Reservation r : reservation) {
                r.setVehicle(reservationDao.getVehicleDao().findById(r.getVehicle().getId()));
                r.setClient(reservationDao.getClientDao().findById(r.getClient().getId()));
            }
            return reservation;
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public int count() throws ServiceException {
        return reservationDao.count();
    }

    public void edit(long id, Reservation newRent) throws ServiceException {
        try {
            LocalDate beginDate = newRent.getDebut();
            LocalDate endDate = newRent.getFin();
            Period diff = Period.between(beginDate, endDate);

            if (diff.getDays() > 7) {
                throw new ServiceException("Une voiture ne peut pas être louée plus de 7 jours d'affilés par un même Client");
            }
            reservationDao.update(id, newRent);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }

    public List<Client> findAllClientsPerVehicleId(long vehicleId) throws ServiceException {
        try {
            return reservationDao.findAllClientsPerVehicleId(vehicleId);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }

    public long delete(Reservation reservation) throws ServiceException {
        try {
            return reservationDao.delete(reservation);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }
}