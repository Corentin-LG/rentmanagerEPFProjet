package com.epf.rentmanager.dao;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationDao {

    private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
    private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
    private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
    private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
    private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
    private static final String COUNT_RESERVATIONS_QUERY = "SELECT COUNT(id) AS count FROM Reservation;";
    private static final String FIND_RESERVATION_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation WHERE id=?;";
    private static ReservationDao instance = null;

    private ReservationDao() {
    }

    public static ReservationDao getInstance() {
        if (instance == null) {
            instance = new ReservationDao();
        }
        return instance;
    }

    public long create(Reservation reservation) throws DaoException {
        return 0;
    }

    public long delete(Reservation reservation) throws DaoException {
        return 0;
    }

    public List<Reservation> findResaByClientId(long clientId) throws DaoException {
        List<Reservation> reservation = new ArrayList<Reservation>();
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
            preparedStatement.setInt(1, (int) clientId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                long id = (rs.getInt("id"));
                long vehicleId = (rs.getInt("vehicle_id"));
                Client client = new Client(clientId);
                Vehicle vehicle = new Vehicle(vehicleId);

                LocalDate debut = (rs.getDate("debut").toLocalDate());
                LocalDate fin = (rs.getDate("fin").toLocalDate());

                reservation.add(new Reservation(id, client, vehicle, debut, fin));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
        return reservation;
    }

    public List<Reservation> findResaByVehicleId(long vehicleId) throws DaoException, ServiceException {
        List<Reservation> reservation = new ArrayList<Reservation>();
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
            preparedStatement.setInt(1, (int) vehicleId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                long id = (rs.getInt("id"));
                long clientId = (rs.getInt("client_id"));
                Client client = new Client(clientId);
                Vehicle vehicle = new Vehicle(vehicleId);

                LocalDate debut = (rs.getDate("debut").toLocalDate());
                LocalDate fin = (rs.getDate("fin").toLocalDate());

                reservation.add(new Reservation(id, client, vehicle, debut, fin));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
        return reservation;
    }

    public List<Reservation> findAll() throws DaoException {
        List<Reservation> reservation = new ArrayList<Reservation>();
        try {
            Connection connection = ConnectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(FIND_RESERVATIONS_QUERY);
            while (rs.next()) {
                long id = (rs.getInt("id"));
                long clientId = (rs.getInt("client_id"));
                long vehicleId = (rs.getInt("vehicle_id"));
                Client client = new Client(clientId);
                Vehicle vehicle = new Vehicle(vehicleId);

                LocalDate debut = (rs.getDate("debut").toLocalDate());
                LocalDate fin = (rs.getDate("fin").toLocalDate());

                reservation.add(new Reservation(id, client, vehicle, debut, fin));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
        return reservation;
    }

    public int count() {
        int nbReservations = 0;
        try {
            Connection connection = ConnectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(COUNT_RESERVATIONS_QUERY);
            if (rs.next()) {
                nbReservations = rs.getInt("count");
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nbReservations;
    }

    public Reservation findById(long id) throws DaoException {
        Reservation reservation = null;
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_RESERVATION_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                reservation = new Reservation(id, ClientService.getInstance().findById(rs.getLong("client_id")),
                        VehicleService.getInstance().findById(rs.getLong("vehicle_id")),
                        rs.getDate("debut").toLocalDate(), rs.getDate("fin").toLocalDate());
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException | ServiceException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
        return reservation;
    }
}
