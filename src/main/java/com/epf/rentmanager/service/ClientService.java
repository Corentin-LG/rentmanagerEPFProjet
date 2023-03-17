package com.epf.rentmanager.service;
// le service

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private ClientDao clientDao;
    public ClientService(ClientDao clientDao){
        this.clientDao = clientDao;
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
            return clientDao.create(client);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }

    public Client findById(long id) throws ServiceException {
        try {
            return clientDao.findById(id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public List<Client> findAll() throws ServiceException {
        try {
            return clientDao.findAll();
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
