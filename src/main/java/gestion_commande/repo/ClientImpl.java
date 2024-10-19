package gestion_commande.repo;

import gestion_commande.interfaces.GenerInerface;
import gestion_commande.models.Admin;
import gestion_commande.models.Client;
import gestion_commande.utilis.EntityManagerUtil;
import gestion_commande.utilis.LoggerMessage;
import gestion_commande.utilis.PasswordUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class ClientImpl implements GenerInerface<Client, Long> {

	@Override
	public void create(Client entity) {
	    EntityManager em = EntityManagerUtil.getEntityManager();
	    EntityTransaction transaction = em.getTransaction();

	    try {
	        String hashedPassword = PasswordUtil.hashPassword(entity.getMotDePasse());
	        entity.setMotDePasse(hashedPassword);
	        
	        transaction.begin();
	        em.persist(entity); 
	        transaction.commit();
	        LoggerMessage.info("Client créé avec succès. ID: " + entity.getId());
	    } catch (Exception e) {
	        if (transaction.isActive()) {
	            transaction.rollback();
	        }
	        LoggerMessage.error("Erreur lors de la création de client: " + e.getMessage());
	        throw e;
	    } finally {
	        em.close();
	    }
	}


    @Override
    public Optional<Client> findById(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            Client client = em.find(Client.class, id);
            return Optional.ofNullable(client);
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Client entity) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(entity); 
            transaction.commit();
            LoggerMessage.info("Client mis à jour avec succès. ID: " + entity.getId());
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LoggerMessage.error("Erreur lors de la mise à jour du client: " + e.getMessage());
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Client client = em.find(Client.class, id);
            if (client != null) {
                em.remove(client);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LoggerMessage.error("Erreur lors de la suppression du client: " + e.getMessage());
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Client> getAll() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            return em.createQuery("FROM Client", Client.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Client> getPage(int page, int pageSize) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            return em.createQuery("FROM Client", Client.class)
                    .setFirstResult((page - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Long count() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            Long count = (Long) em.createQuery("SELECT COUNT(c) FROM Client c").getSingleResult();
            return (long) count.intValue();
        } finally {
            em.close();
        }
    }
}
