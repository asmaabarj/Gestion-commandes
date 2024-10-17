package gestion_commande.services;

import gestion_commande.models.Admin;
import gestion_commande.repo.AdminImpl;
import java.util.List;
import java.util.Optional;

public class AdminService {
    
    private AdminImpl adminRepo = new AdminImpl();
    
    public void create(Admin admin) {
        adminRepo.create(admin);
    }
    
    public Optional<Admin> findById(Long id) {
        return adminRepo.findById(id);
    }
    
    public List<Admin> getAll() {
        return adminRepo.getAll();
    }

    public List<Admin> getPage(int page, int pageSize) {
        return adminRepo.getPage(page, pageSize);
    }
    
    public void delete(Long id) {
    	adminRepo.delete(id);
    }
    
    public Integer count() {
        return adminRepo.count();
    }
}
