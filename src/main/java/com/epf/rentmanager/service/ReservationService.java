package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;

import java.util.List;

public class ReservationService {

        public static ReservationService instance;
        private final ReservationDao reservationDao;

        private ReservationService() {
            this.reservationDao = ReservationDao.getInstance();
        }

        public static ReservationService getInstance() {
            if (instance == null) {
                instance = new ReservationService();
            }

            return instance;
        }


        public long create(Vehicle vehicle) throws ServiceException {
            // TODO: créer un véhicule

            return 0;
        }

        public Reservation findById(long id) throws ServiceException {
            // TODO: récupérer un véhicule par son id

            return new Reservation();
        }

        public List<Reservation> findAll() throws ServiceException {
            // TODO: récupérer tous les clients
            try {
                return ReservationDao.getInstance().findAll();
            } catch (DaoException e) {
                e.printStackTrace();
                throw new ServiceException();
            }
        }

    }