package gestion_commande.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import gestion_commande.models.Admin;
import gestion_commande.repo.AdminImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AdminServiceTest {

    @Mock
    private AdminImpl AdminRepo;

    @InjectMocks
    private AdminService AdminService;

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
    public void testCreateAdmin() {
        Admin Admin = new Admin();
        Admin.setNom("Dupont");
        Admin.setPrenom("Jean");
        Admin.setEmail("jean.dupont@example.com");
        Admin.setNiveauAcces(1);
        Admin.setMotDePasse("password123");
        doNothing().when(AdminRepo).create(Admin);

        AdminService.create(Admin);

        verify(AdminRepo, times(1)).create(Admin);
    }

    @Test
    public void testFindAdminById() {
        Admin Admin = new Admin();
        Admin.setId(1L);
        Admin.setNom("Dupont");

        when(AdminRepo.findById(1L)).thenReturn(Optional.of(Admin));

        Optional<Admin> result = AdminService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("Dupont", result.get().getNom());
        verify(AdminRepo, times(1)).findById(1L);
    }

    @Test
    public void testUpdateAdmin() {
        Admin Admin = new Admin();
        Admin.setId(1L);
        Admin.setNom("Dupont");
        Admin.setPrenom("Jean");
       
        doNothing().when(AdminRepo).update(Admin);

        AdminService.update(Admin);

        verify(AdminRepo, times(1)).update(Admin);
    }

    @Test
    public void testDeleteAdmin() {
        doNothing().when(AdminRepo).delete(1L);

        AdminService.delete(1L);

        verify(AdminRepo, times(1)).delete(1L);
    }

    @Test
    public void testGetAllAdmins() {
        List<Admin> Admins = Arrays.asList(
            new Admin(),
            new Admin()
        );

        when(AdminRepo.getAll()).thenReturn(Admins);

        List<Admin> result = AdminService.getAll();

        assertEquals(2, result.size());
        verify(AdminRepo, times(1)).getAll();
    }

    @Test
    public void testGetAdminPage() {
        List<Admin> Admins = Arrays.asList(
            new Admin(),
            new Admin()
        );

        when(AdminRepo.getPage(1, 10)).thenReturn(Admins);

        List<Admin> result = AdminService.getPage(1, 10);

        assertEquals(2, result.size());
        verify(AdminRepo, times(1)).getPage(1, 10);
    }

    @Test
    public void testCountAdmins() {
        when(AdminRepo.count()).thenReturn(100L);

        Long count = AdminService.count();

        assertEquals(Long.valueOf(100), count);
        verify(AdminRepo, times(1)).count();
    }
}

