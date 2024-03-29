package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VehicleService {
    private final VehicleDao vehicleDao;

    public VehicleService(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    public long create(Vehicle vehicle) throws ServiceException {
        try {
            if (vehicle.getConstructeur().isBlank()) {
                throw new ServiceException("Il n'y a pas de constructeur");
            }
            if (vehicle.getNb_places() <= 0) {
                throw new ServiceException("Une voiture a au moins une place");
            }
            if (vehicle.getNb_places() < 2 || vehicle.getNb_places() > 9) {
                throw new ServiceException("Une voiture doit avoir ici 2 à 9 places");
            }
            return new VehicleDao().create(vehicle);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }

    public Vehicle findById(long id) throws ServiceException {
        try {
            return vehicleDao.findById(id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public List<Vehicle> findAll() throws ServiceException {
        try {
            return vehicleDao.findAll();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public int count() throws ServiceException {
        try {
            return vehicleDao.count();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }

    public void edit(long id, Vehicle newVehicle) throws ServiceException {
        try {
            if (newVehicle.getNb_places() <= 0) {
                throw new ServiceException("Une voiture a au moins une place");
            }
            if (newVehicle.getNb_places() < 2 || newVehicle.getNb_places() > 9) {
                throw new ServiceException("Une voiture doit avoir ici 2 à 9 places");
            }
            vehicleDao.update(id, newVehicle);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }

    public long delete(Vehicle vehicle) throws ServiceException {
        try {
            ReservationService rs = new ReservationService(new ReservationDao());
            for (Reservation rent : rs.findResaByVehicleId(vehicle.getId())) {
                rs.delete(rent);
            }
            return vehicleDao.delete(vehicle);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }
}

