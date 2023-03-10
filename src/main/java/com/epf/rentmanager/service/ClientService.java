package com.epf.rentmanager.service;
// le service

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;

import java.util.List;

public class ClientService {

    public static ClientService instance;
    private final ClientDao clientDao;

    private ClientService() {
        this.clientDao = ClientDao.getInstance();
    }

    public static ClientService getInstance() {
        if (instance == null) {
            instance = new ClientService();
        }
        return instance;
    }

    public long create(Client client) throws ServiceException {
        try {
            if (client.getPrenom().isBlank()) {
                throw new ServiceException("Il n'y a pas de prénom");
            }
            if (client.getNom().isBlank()) {
                throw new ServiceException("Il n'y a pas de nom");
            }
            client.setNom(client.getNom().toUpperCase());
            return ClientDao.getInstance().create(client);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }

    public Client findById(long id) throws ServiceException {
        try {
            return ClientDao.getInstance().findById(id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public List<Client> findAll() throws ServiceException {
        try {
            return ClientDao.getInstance().findAll();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }

    }

    public int count() throws ServiceException {
        try {
            return clientDao.count();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }
}
