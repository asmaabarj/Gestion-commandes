package gestion_commande.repo;

import gestion_commande.interfaces.GenerInerface;
import gestion_commande.models.Admin;
import gestion_commande.utilis.EntityManagerUtil;
import gestion_commande.utilis.LoggerMessage;
import gestion_commande.utilis.PasswordUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class AdminImpl implements GenerInerface<Admin, Long> {

	@Override
	public void create(Admin entity) {
	    EntityManager em = EntityManagerUtil.getEntityManager();
	    EntityTransaction transaction = em.getTransaction();

	    try {
	        String hashedPassword = PasswordUtil.hashPassword(entity.getMotDePasse());
	        entity.setMotDePasse(hashedPassword);
	        
	        transaction.begin();
	        em.persist(entity); 
	        transaction.commit();
	        LoggerMessage.info("Admin créé avec succès. ID: " + entity.getId());
	    } catch (Exception e) {
	        if (transaction.isActive()) {
	            transaction.rollback();
	        }
	        LoggerMessage.error("Erreur lors de la création de l'admin: " + e.getMessage());
	        throw e;
	    } finally {
	        em.close();
	    }
	}


    @Override
    public Optional<Admin> findById(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            Admin admin = em.find(Admin.class, id);
            return Optional.ofNullable(admin);
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Admin entity) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(entity); 
            transaction.commit();
            LoggerMessage.info("Admin mis à jour avec succès. ID: " + entity.getId());
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LoggerMessage.error("Erreur lors de la mise à jour du admin: " + e.getMessage());
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
            Admin admin = em.find(Admin.class, id);
            if (admin != null) {
                em.remove(admin);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LoggerMessage.error("Erreur lors de la suppression du admin: " + e.getMessage());
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Admin> getAll() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            return em.createQuery("FROM Admin", Admin.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Admin> getPage(int page, int pageSize) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            return em.createQuery("FROM Admin", Admin.class)
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
            Long count = (Long) em.createQuery("SELECT COUNT(c) FROM Admin c").getSingleResult();
            return (long) count.intValue();
        } finally {
            em.close();
        }
    }
}
