package com.epf.rentmanager.ui.cli;

import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.utils.IOUtils;

import java.util.List;

public class UIVehicle {
    public static void listVehicles() {
        try {
            for (Vehicle vehicle : new VehicleService(new VehicleDao()).findAll()) {
                IOUtils.print(vehicle.toString());
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public static void createVehicle() {
        Vehicle vehicle = new Vehicle();
        IOUtils.print("Création d'un véhicule");
        vehicle.setConstructeur(IOUtils.readString("Entrez le nom du contructeur : ", true));
        vehicle.setModele(IOUtils.readString("Entrez le nom du modele : ", true));
        vehicle.setNb_places(IOUtils.readInt("Entrez le nombre de places : "));
        try {
            long ID = new VehicleService(new VehicleDao()).create(vehicle);
            IOUtils.print("Le véhicule [" + ID + "] a été créé");
        } catch (ServiceException e) {
            e.printStackTrace();
            IOUtils.print("Le véhicule n'a pas pu être créé");
        }
    }

    public static Vehicle selectVehicle() throws ServiceException {
        List<Vehicle> vehicleList = new VehicleService(new VehicleDao()).findAll();
        IOUtils.print("Sélectionner un véhicule");
        int index;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        do {
            for (Vehicle vehicle : vehicleList) {
                long id = vehicle.getId();
                IOUtils.print(" [" + id + "] " + vehicle);
                min = Integer.min(min, (int) id);
                max = Integer.max(max, (int) id);
            }
            index = IOUtils.readInt("Entrez un indice :");
        } while (index < min || index >= max);
        return vehicleList.get(index - 1);
    }
}
