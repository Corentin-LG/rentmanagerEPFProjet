package com.epf.rentmanager.dao;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationDao {

    private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
    private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
    private static final String UPDATE_RESERVATION_QUERY = "UPDATE Reservation SET client_id=?, vehicle_id=?, debut=?, fin=? WHERE id=?;";
    private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
    private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
    private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
    private static final String COUNT_RESERVATIONS_QUERY = "SELECT COUNT(id) AS count FROM Reservation;";
    private static final String FIND_RESERVATION_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation WHERE id=?;";
    private static final String FIND_VEHICLES_RENTED_BY_CLIENT = "SELECT Vehicle.id, constructeur, modele, nb_places FROM Reservation INNER JOIN Vehicle ON Reservation.vehicle_id = Vehicle.id WHERE Reservation.client_id=?;";
    private static final String FIND_ALL_CLIENTS_PER_VEHICLE = "SELECT Client.id, nom, prenom, email, naissance FROM Reservation INNER JOIN Client ON Reservation.client_id = Client.id WHERE Reservation.vehicle_id=?;";
    private ClientDao clientDao;
    private VehicleDao vehicleDao;

    public ReservationDao() {
        clientDao = new ClientDao();
        vehicleDao = new VehicleDao();
    }

    public ClientDao getClientDao() {
        return clientDao;
    }

    public void setClientDao(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public VehicleDao getVehicleDao() {
        return vehicleDao;
    }

    public void setVehicleDao(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    public long create(Reservation reservation) throws DaoException {
        long ID = 0;
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_RESERVATION_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, reservation.getClient().getId());
            preparedStatement.setLong(2, reservation.getVehicle().getId());
            preparedStatement.setDate(3, Date.valueOf(reservation.getDebut()));
            preparedStatement.setDate(4, Date.valueOf(reservation.getFin()));
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
                reservation = new Reservation(id, clientDao.findById(rs.getLong("client_id")),
                        vehicleDao.findById(rs.getLong("vehicle_id")),
                        rs.getDate("debut").toLocalDate(), rs.getDate("fin").toLocalDate());
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException | DaoException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
        return reservation;
    }

    public void update(long id, Reservation newRent) throws DaoException {
        try (
                Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RESERVATION_QUERY)
        ) {
            preparedStatement.setLong(1, newRent.getClient().getId());
            preparedStatement.setLong(2, newRent.getVehicle().getId());
            preparedStatement.setDate(3, Date.valueOf(newRent.getDebut()));
            preparedStatement.setDate(4, Date.valueOf(newRent.getFin()));
            preparedStatement.setLong(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
    }

    public List<Vehicle> findAllVehiclesPerClientId(long clientId) throws DaoException {
        List<Vehicle> allVehicles = new ArrayList<>();
        try (
                Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_VEHICLES_RENTED_BY_CLIENT)
        ) {
            preparedStatement.setLong(1, clientId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                allVehicles.add(new Vehicle(rs.getLong("Vehicle.id"),
                        rs.getString("constructeur"),
                        rs.getString("modele"),
                        rs.getShort("nb_places")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
        return allVehicles;
    }

    public List<Client> findAllClientsPerVehicleId(long vehicleId) throws DaoException {
        List<Client> rentedVehicles = new ArrayList<>();
        try (
                Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_CLIENTS_PER_VEHICLE)
        ) {
            preparedStatement.setLong(1, vehicleId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                rentedVehicles.add(
                        new Client(rs.getLong("Client.id"), rs.getString("nom"),
                                rs.getString("prenom"), rs.getString("email"),
                                rs.getDate("naissance").toLocalDate()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
        return rentedVehicles;
    }
}
