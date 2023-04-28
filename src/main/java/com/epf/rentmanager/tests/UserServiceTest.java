package com.epf.rentmanager.tests;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientDao clientDao;

    @Test
    public void findAll_should_fail_when_dao_throws_exception() throws DaoException {
        // When
        when(this.clientDao.findAll()).thenThrow(DaoException.class);

        // Then
        assertThrows(ServiceException.class, () -> clientService.findAll());
    }

    @Test
    public void find_by_id_must_work() throws ServiceException {
        // setup
        Client ExpectedClient = new Client(1515, "John", "Doe", "john.doe@example.com", LocalDate.parse("2000-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        when(this.clientService.findById(1515)).thenReturn(ExpectedClient);

        // execute
        Client actualClient = this.clientService.findById(1515);

        // verify
        assertEquals(ExpectedClient.getId(), actualClient.getId());
        assertEquals(ExpectedClient.getPrenom(), actualClient.getPrenom());
        assertEquals(ExpectedClient.getNom(), actualClient.getNom());
        assertEquals(ExpectedClient.getEmail(), actualClient.getEmail());
        assertEquals(ExpectedClient.getNaissance(), actualClient.getNaissance());
    }

    @Test
    public void create_must_work() throws ServiceException, DaoException {
        // Arrange
        ClientDao clientDao = mock(ClientDao.class);
        when(clientDao.countSameEmail(anyString())).thenReturn(0);
        ClientService service = new ClientService(clientDao);
        Client client = new Client(1515, "John", "Doe", "john.doe@example.com", LocalDate.parse("2000-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        // Act
        service.create(client);

        // Assert
        verify(clientDao, times(1)).create(client);
    }

    @Test(expected = ServiceException.class)
    public void create_client_with_existing_email() throws ServiceException {
        // given
        ClientService clientService = new ClientService(clientDao);
        Client client = new Client(1515, "John", "Doe", "john.doe@example.com", LocalDate.parse("2000-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        // when
        try {
            when(clientDao.countSameEmail(client.getEmail())).thenReturn(1);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        clientService.create(client);

        // then - expected ServiceException
    }

    @Test(expected = ServiceException.class)
    public void create_client_with_short_first_name() throws ServiceException {
        // given
        ClientService clientService = new ClientService(clientDao);
        Client client = new Client(1515, "John", "D", "john.doe@example.com", LocalDate.parse("2000-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        // when
        clientService.create(client);

        // then - expected ServiceException
    }


}
