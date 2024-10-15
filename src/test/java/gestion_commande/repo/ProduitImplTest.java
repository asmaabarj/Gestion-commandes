package gestion_commande.repo;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import gestion_commande.models.Produit;
import gestion_commande.utilis.LoggerMessage;

import java.util.List;
import java.util.Optional;

public class ProduitImplTest {

	private ProduitImpl produitImpl;
	private Produit testProduit;

	@Before
	public void setUp() throws Exception {
		produitImpl = new ProduitImpl();
		testProduit = new Produit();
		testProduit.setNom("Produit Test");
		testProduit.setDescription("Description azz");
		testProduit.setPrix(100.0);
		testProduit.setStock(10);
	}

	@After
	public void tearDown() throws Exception {
		LoggerMessage.info("Succes");
	}

	@Test
	public void testCreate() {
		produitImpl.create(testProduit);
		assertNotNull(testProduit.getId());
	}

	@Test
	public void testFindById() {
		produitImpl.create(testProduit);
		Optional<Produit> foundProduit = produitImpl.findById(testProduit.getId());
		assertTrue(foundProduit.isPresent());
		assertEquals(testProduit.getNom(), foundProduit.get().getNom());
	}

	@Test
	public void testUpdate() {
		produitImpl.create(testProduit);
		testProduit.setNom("Produit Modifié");
		produitImpl.update(testProduit);
		Optional<Produit> updatedProduit = produitImpl.findById(testProduit.getId());
		assertTrue(updatedProduit.isPresent());
		assertEquals("Produit Modifié", updatedProduit.get().getNom());
	}

	@Test
	public void testDelete() {
		produitImpl.create(testProduit);
		Long id = testProduit.getId();
		produitImpl.delete(id);
		Optional<Produit> deletedProduit = produitImpl.findById(id);
		assertFalse(deletedProduit.isPresent());
	}

	@Test
	public void testGetAll() {
		produitImpl.create(testProduit);
		Produit p = new Produit();
		p.setNom("Autre Produit");
		testProduit.setDescription("Description getAll");
		p.setPrix(200.0);
		p.setStock(20);
		produitImpl.create(p);
		List<Produit> allProduits = produitImpl.getAll();
		assertFalse(allProduits.isEmpty());
		assertEquals(2, allProduits.size());
	}

	@Test
	public void testGetPage() {
		for (int i = 0; i < 6; i++) {
			Produit p = new Produit();
			p.setNom("Produit " + i);
			p.setPrix(100.0 + i);
			p.setDescription("Description page getAll "+i);
			p.setStock(i);
			produitImpl.create(p);
		}
		List<Produit> page = produitImpl.getPage(1, 5);
		assertEquals(5, page.size());
	}

	@Test
	public void testCount() {
		Long initialCount = produitImpl.count();
		produitImpl.create(testProduit);
		Produit p = new Produit();
		p.setNom("Autre Produit");
		p.setPrix(200.0);
		p.setStock(15);
		produitImpl.create(p);
		Long newCount = produitImpl.count();
		assertEquals(initialCount + 2, newCount.longValue());
		LoggerMessage.info("Succes count = " + initialCount + " " + newCount);
	}
	

}
