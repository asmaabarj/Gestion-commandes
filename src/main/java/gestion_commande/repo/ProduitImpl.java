package gestion_commande.repo;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import gestion_commande.interfaces.GenerInerface;
import gestion_commande.models.Produit;
import gestion_commande.utilis.EntityManagerUtil;
import gestion_commande.utilis.LoggerMessage;

public class ProduitImpl implements GenerInerface<Produit, Long> {

	
	public ProduitImpl() {
		
	}
	
	
	
	@Override
	public void create(Produit entity) {
		EntityManager em = EntityManagerUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();
			em.persist(entity);
			em.flush();
			transaction.commit();
			LoggerMessage.info("Produit ajouté avec succès. ID: " + entity.getId());
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			LoggerMessage.error("Erreur lors de l'ajout du Produit: " + e.getMessage());
			throw e;
		} finally {
			em.close();
		}
	}

	@Override
	public Optional<Produit> findById(Long id) {
		EntityManager em = EntityManagerUtil.getEntityManager();
		try {
			Produit produit = em.find(Produit.class, id);
			return Optional.ofNullable(produit);
		} finally {
			em.close();
		}
	}

	@Override
	public void update(Produit entity) {
		EntityManager em = EntityManagerUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();
			em.merge(entity);
			transaction.commit();
			LoggerMessage.info("Produit mis à jour avec succès. ID: " + entity.getId());
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			LoggerMessage.error("Erreur lors de la mise à jour du Produit: " + e.getMessage());
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
			Produit produit = em.find(Produit.class, id);
			if (produit != null) {
				em.remove(produit);
				transaction.commit();
				LoggerMessage.info("Produit supprimé avec succès. ID: " + id);
			} else {
				LoggerMessage.warn("Tentative de suppression d'un Produit inexistant. ID: " + id);
			}
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			LoggerMessage.error("Erreur lors de la suppression du Produit: " + e.getMessage());
			throw e;
		} finally {
			em.close();
		}
	}

	@Override
    @SuppressWarnings("unchecked")
	public List<Produit> getAll() {
		EntityManager em = EntityManagerUtil.getEntityManager();
		try {
			Query query = em.createQuery("SELECT p FROM Produit p", Produit.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	@Override
    @SuppressWarnings("unchecked")
	public List<Produit> getPage(int page, int pageSize) {
		EntityManager em = EntityManagerUtil.getEntityManager();
		try {
			Query query = em.createQuery("SELECT p FROM Produit p", Produit.class);
		     query.setFirstResult((page - 1) * pageSize);
	            query.setMaxResults(pageSize);

	           return query.getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public Long count() {
		EntityManager em = EntityManagerUtil.getEntityManager();
		try {
			Query query = em.createQuery("SELECT COUNT(p) FROM Produit p");
			return (Long) query.getSingleResult();
		} finally {
			em.close();
		}
	}

	public Integer countAsInteger() {
		return count().intValue();
	}

}
