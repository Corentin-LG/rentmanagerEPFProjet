package com.epf.rentmanager.dao;
// le dao

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientDao {

    private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
    private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
    private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
    private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
    private static final String COUNT_CLIENTS_QUERY = "SELECT COUNT(id) AS count FROM Client;";
    private static final String COUNT_SAME_EMAIL_QUERY = "SELECT COUNT(email) AS count FROM Client WHERE email=?;";
    private static final String UPDATE_CLIENT_QUERY = "UPDATE Client SET nom=?, prenom=?, email=?, naissance=? WHERE id=?;";

    public ClientDao() {
    }

    public long create(Client client) throws DaoException {
        long ID
                = 0;
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CLIENT_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, client.getNom());
            preparedStatement.setString(2, client.getPrenom());
            preparedStatement.setString(3, client.getEmail());
            preparedStatement.setDate(4, Date.valueOf(client.getNaissance()));
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

    public long delete(Client client) throws DaoException {
        long clientId = client.getId();
        try (
                Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT_QUERY)
        ) {
            preparedStatement.setLong(1, clientId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
        return clientId;
    }

    public Client findById(long id) throws DaoException {
        Client client = new Client();
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_CLIENT_QUERY);
            preparedStatement.setInt(1, (int) id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                client.setId(id);
                client.setNom(rs.getString("nom"));
                client.setPrenom(rs.getString("prenom"));
                client.setEmail(rs.getString("email"));
                client.setNaissance(rs.getDate("naissance").toLocalDate());
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
        return client;
    }

    public List<Client> findAll() throws DaoException {
        List<Client> clients = new ArrayList<Client>();
        try {
            Connection connection = ConnectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(FIND_CLIENTS_QUERY);
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String email = rs.getString("email");
                LocalDate date = rs.getDate("naissance").toLocalDate();
                clients.add(new Client(id, nom, prenom, email, date));

            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
        return clients;
    }

    public int count() throws DaoException {
        int nbClients = 0;
        try {
            Connection connection = ConnectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(COUNT_CLIENTS_QUERY);
            if (rs.next()) {
                nbClients = rs.getInt("count");
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return nbClients;
    }

    public int countSameEmail() throws DaoException {
        int nbClients = 0;
        try {
            Connection connection = ConnectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(COUNT_SAME_EMAIL_QUERY);
            if (rs.next()) {
                nbClients = rs.getInt("count");
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return nbClients;
    }

    public int countSameEmail(String email) throws DaoException {
        int nbClients = 0;
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(COUNT_SAME_EMAIL_QUERY);
            preparedStatement.setString(1, email);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                nbClients = rs.getInt("count");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
        return nbClients;
    }

    public void update(long id, Client newClient) throws DaoException {
        try (
                Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENT_QUERY)
        ) {
            preparedStatement.setString(1, newClient.getNom());
            preparedStatement.setString(2, newClient.getPrenom());
            preparedStatement.setString(3, newClient.getEmail());
            preparedStatement.setDate(4, Date.valueOf(newClient.getNaissance()));
            preparedStatement.setLong(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
    }
}
