package gestion_commande.services;

import gestion_commande.models.Client;
import gestion_commande.repo.ClientImpl;
import java.util.List;
import java.util.Optional;

public class ClientService {
    
    private ClientImpl clientRepo = new ClientImpl();
    
    public void create(Client client) {
        clientRepo.create(client);
    }
        
    public void update(Client client) {
    	clientRepo.update(client);
    }
    
    public Optional<Client> findById(Long id) {
        return clientRepo.findById(id);
    }
    
    public List<Client> getAll() {
        return clientRepo.getAll();
    }

    public List<Client> getPage(int page, int pageSize) {
        return clientRepo.getPage(page, pageSize);
    }
    
    public void delete(Long id) {
        clientRepo.delete(id);
    }
    
    public Long count() {
        return clientRepo.count();
    }
}
