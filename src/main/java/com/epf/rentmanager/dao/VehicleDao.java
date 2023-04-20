package com.epf.rentmanager.dao;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class VehicleDao {

    private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, modele, nb_places) VALUES(?, ?, ?);";
    private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
    private static final String FIND_VEHICLE_QUERY= "SELECT id, constructeur, modele, nb_places FROM Vehicle WHERE id=?;";
    private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, modele, nb_places FROM Vehicle;";
    private static final String COUNT_VEHICLES_QUERY = "SELECT COUNT(id) AS count FROM Vehicle;";
    public VehicleDao() {
    }

    public long create(Vehicle vehicle) throws DaoException {
        long ID = 0;
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_VEHICLE_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, vehicle.getConstructeur());
            preparedStatement.setString(2, vehicle.getModele());
            preparedStatement.setInt(3, vehicle.getNb_places());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            while (rs.next()) {
                ID = rs.getLong("id");
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
        return ID;
    }

    public long delete(Vehicle vehicle) throws DaoException {
        return 0;
    }

    public Vehicle findById(long id) throws DaoException {
        Vehicle vehicle = new Vehicle();
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_VEHICLE_QUERY);
            preparedStatement.setInt(1, (int) id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                vehicle.setId(id);
                vehicle.setConstructeur(rs.getString("constructeur"));
                vehicle.setModele(rs.getString("modele")); //wip
                vehicle.setNb_places(rs.getInt("nb_places"));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
        return vehicle;
    }

    public List<Vehicle> findAll() throws DaoException {
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        try {
            Connection connection = ConnectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(FIND_VEHICLES_QUERY);
            while (rs.next()) {
                long id = (rs.getInt("id"));
                String constructeur = (rs.getString("constructeur"));
                String modele = (rs.getString("modele")); //wip
                int nb_places = (rs.getInt("nb_places"));

                vehicles.add(new Vehicle(id, constructeur, modele, nb_places)); //wip
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
        return vehicles;
    }

    public int count() throws DaoException {
        int nbVehicle = 0;
        try{
            Connection connection = ConnectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(COUNT_VEHICLES_QUERY);
            if (rs.next()) {
                nbVehicle = rs.getInt("count");
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return nbVehicle;
    }
}
