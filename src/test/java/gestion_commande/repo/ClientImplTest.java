package gestion_commande.repo;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import gestion_commande.models.Client;
import gestion_commande.utilis.LoggerMessage;

import java.util.List;
import java.util.Optional;

public class ClientImplTest {

    private ClientImpl clientImpl;
    private Client testClient;

    @Before
    public void setUp() throws Exception {
        clientImpl = new ClientImpl();
        testClient = new Client();
        testClient.setNom("sanaa");
        testClient.setPrenom("sanaa");
        testClient.setEmail("sanaa@example.com");
        testClient.setMotDePasse("123456");
        testClient.setAdresseLivraison("123 rue de Test");
        testClient.setMoyenPaiement("Carte de crédit");
    }

    @After
    public void tearDown() throws Exception {
        LoggerMessage.info("Test terminé avec succès");
    }

    @Test
    public void testCreate() {
        clientImpl.create(testClient);
        assertNotNull(testClient.getId());
    }

    @Test
    public void testFindById() {
        clientImpl.create(testClient);
        Optional<Client> foundClient = clientImpl.findById(Long.valueOf(testClient.getId())); 
        assertTrue(foundClient.isPresent());
        assertEquals(testClient.getNom(), foundClient.get().getNom());
    }

    @Test
    public void testUpdate() {
        clientImpl.create(testClient);
        testClient.setNom("Nom Modifié");
        clientImpl.update(testClient);
        Optional<Client> updatedClient = clientImpl.findById(testClient.getId());
        assertTrue(updatedClient.isPresent());
        assertEquals("Nom Modifié", updatedClient.get().getNom());
    }

    @Test
    public void testDelete() {
        clientImpl.create(testClient);
        Long id = Long.valueOf(testClient.getId()); 
        clientImpl.delete(id);
        Optional<Client> deletedClient = clientImpl.findById(id);
        assertFalse(deletedClient.isPresent());
    }

    @Test
    public void testGetAll() {
        clientImpl.create(testClient);
        Client anotherClient = new Client();
        anotherClient.setNom("Autre Client");
        anotherClient.setPrenom("Autre Prenom");
        anotherClient.setEmail("autre@example.com");
        anotherClient.setMotDePasse("654321");
        anotherClient.setAdresseLivraison("456 rue de Exemple");
        anotherClient.setMoyenPaiement("Paypal");
        clientImpl.create(anotherClient);
        List<Client> allClients = clientImpl.getAll();
        assertFalse(allClients.isEmpty());
        assertEquals(2, allClients.size());
    }

    @Test
    public void testGetPage() {
        for (int i = 0; i < 6; i++) {
            Client c = new Client();
            c.setNom("Client " + i);
            c.setPrenom("Prenom " + i);
            c.setEmail("client" + i + "@example.com");
            c.setMotDePasse("password" + i);
            c.setAdresseLivraison("Adresse " + i);
            c.setMoyenPaiement("Paiement " + i);
            clientImpl.create(c);
        }
        List<Client> page = clientImpl.getPage(1, 5);
        assertEquals(5, page.size());
    }

    @Test
    public void testCount() {
        int initialCount = clientImpl.count();
        clientImpl.create(testClient);
        Client anotherClient = new Client();
        anotherClient.setNom("Autre Client");
        anotherClient.setPrenom("Autre Prenom");
        anotherClient.setEmail("autre2@example.com");
        anotherClient.setMotDePasse("789012");
        anotherClient.setAdresseLivraison("789 rue de Exemple");
        anotherClient.setMoyenPaiement("Virement bancaire");
        clientImpl.create(anotherClient);
        int newCount = clientImpl.count();
        assertEquals(initialCount + 2, newCount);
        LoggerMessage.info("Test count: initialCount=" + initialCount + ", newCount=" + newCount);
    }
}
