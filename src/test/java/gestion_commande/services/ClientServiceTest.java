package gestion_commande.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import gestion_commande.models.Client;
import gestion_commande.repo.ClientImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ClientServiceTest {

    @Mock
    private ClientImpl clientRepo;

    @InjectMocks
    private ClientService clientService;

    private AutoCloseable closeable;

    @Before
    public void setUp() throws Exception {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void testCreateClient() {
        Client client = new Client();
        client.setNom("Dupont");
        client.setPrenom("Jean");
        client.setEmail("jean.dupont@example.com");
        client.setAdresseLivraison("123 Rue de Paris");
        client.setMoyenPaiement("Carte Bancaire");
        client.setMotDePasse("password123");
        doNothing().when(clientRepo).create(client);

        clientService.create(client);

        verify(clientRepo, times(1)).create(client);
    }

    @Test
    public void testFindClientById() {
        Client client = new Client();
        client.setId(1L);
        client.setNom("Dupont");

        when(clientRepo.findById(1L)).thenReturn(Optional.of(client));

        Optional<Client> result = clientService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("Dupont", result.get().getNom());
        verify(clientRepo, times(1)).findById(1L);
    }

    @Test
    public void testUpdateClient() {
        Client client = new Client();
        client.setId(1L);
        client.setNom("Dupont");
        client.setPrenom("Jean");
       
        doNothing().when(clientRepo).update(client);

        clientService.update(client);

        verify(clientRepo, times(1)).update(client);
    }

    @Test
    public void testDeleteClient() {
        doNothing().when(clientRepo).delete(1L);

        clientService.delete(1L);

        verify(clientRepo, times(1)).delete(1L);
    }

    @Test
    public void testGetAllClients() {
        List<Client> clients = Arrays.asList(
            new Client(),
            new Client()
        );

        when(clientRepo.getAll()).thenReturn(clients);

        List<Client> result = clientService.getAll();

        assertEquals(2, result.size());
        verify(clientRepo, times(1)).getAll();
    }

    @Test
    public void testGetClientPage() {
        List<Client> clients = Arrays.asList(
            new Client(),
            new Client()
        );

        when(clientRepo.getPage(1, 10)).thenReturn(clients);

        List<Client> result = clientService.getPage(1, 10);

        assertEquals(2, result.size());
        verify(clientRepo, times(1)).getPage(1, 10);
    }

    @Test
    public void testCountClients() {
        when(clientRepo.count()).thenReturn(100L);

        Long count = clientService.count();

        assertEquals(Long.valueOf(100), count);
        verify(clientRepo, times(1)).count();
    }
}

