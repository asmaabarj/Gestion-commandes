package gestion_commande.repo;

import gestion_commande.models.Admin;
import gestion_commande.utilis.LoggerMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.*;

public class AdminImplTest {

    private AdminImpl adminImpl;
    private Admin testAdmin;

    @Before
    public void setUp() throws Exception {
        adminImpl = new AdminImpl();
        testAdmin = new Admin();
        testAdmin.setNom("Admin Test");
        testAdmin.setPrenom("Prenom Test");
        testAdmin.setEmail("admin@example.com");
        testAdmin.setMotDePasse("admin123");
    }

    @After
    public void tearDown() throws Exception {
        LoggerMessage.info("Test Admin terminé avec succès");
    }

    @Test
    public void testCreate() {
        adminImpl.create(testAdmin);
        assertNotNull(testAdmin.getId());
    }

    @Test
    public void testFindById() {
        adminImpl.create(testAdmin);
        Optional<Admin> foundAdmin = adminImpl.findById(testAdmin.getId());
        assertTrue(foundAdmin.isPresent());
        assertEquals(testAdmin.getNom(), foundAdmin.get().getNom());
    }

    @Test
    public void testUpdate() {
        adminImpl.create(testAdmin);
        testAdmin.setNom("Nom Admin Modifié");
        adminImpl.update(testAdmin);
        Optional<Admin> updatedAdmin = adminImpl.findById(testAdmin.getId());
        assertTrue(updatedAdmin.isPresent());
        assertEquals("Nom Admin Modifié", updatedAdmin.get().getNom());
    }

    @Test
    public void testDelete() {
        adminImpl.create(testAdmin);
        Long id = testAdmin.getId();
        adminImpl.delete(id);
        Optional<Admin> deletedAdmin = adminImpl.findById(id);
        assertFalse(deletedAdmin.isPresent());
    }

    @Test
    public void testGetPage() {
        for (int i = 0; i < 6; i++) {
            Admin admin = new Admin();
            admin.setNom("Admin " + i);
            admin.setPrenom("Prenom " + i);
            admin.setEmail("admin" + i + "@example.com");
            admin.setMotDePasse("password" + i);
            adminImpl.create(admin);
        }

        List<Admin> page = adminImpl.getPage(1, 5);
        assertEquals(5, page.size());
        assertEquals("Admin 0", page.get(0).getNom());

        List<Admin> secondPage = adminImpl.getPage(2, 5);
        assertEquals(1, secondPage.size());
        assertEquals("Admin 5", secondPage.get(0).getNom());
    }

    @Test
    public void testGetAll() {
        adminImpl.create(testAdmin);
        Admin anotherAdmin = new Admin();
        anotherAdmin.setNom("Autre Admin");
        anotherAdmin.setPrenom("Autre Prenom");
        anotherAdmin.setEmail("autre.admin@example.com");
        anotherAdmin.setMotDePasse("admin456");
        adminImpl.create(anotherAdmin);

        List<Admin> allAdmins = adminImpl.getAll();
        assertFalse(allAdmins.isEmpty());
        assertEquals(2, allAdmins.size());
    }

    @Test
    public void testCount() {
        int initialCount = adminImpl.count();
        adminImpl.create(testAdmin);
        Admin anotherAdmin = new Admin();
        anotherAdmin.setNom("Autre Admin");
        anotherAdmin.setPrenom("Autre Prenom");
        anotherAdmin.setEmail("autre.admin2@example.com");
        anotherAdmin.setMotDePasse("admin789");
        adminImpl.create(anotherAdmin);

        int newCount = adminImpl.count();
        assertEquals(initialCount + 2, newCount);
        LoggerMessage.info("Test count: initialCount=" + initialCount + ", newCount=" + newCount);
    }
}
