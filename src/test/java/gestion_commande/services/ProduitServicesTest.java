package gestion_commande.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import gestion_commande.models.Produit;
import gestion_commande.repo.ProduitImpl;

public class ProduitServicesTest {

	@Mock
	private ProduitImpl produitImp;

	@InjectMocks
	private ProduitServices produitServices;

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
	public void testCreateProduit() {
		Produit newProduct = new Produit();
		newProduct.setNom("test");
		newProduct.setDescription("desc");
		newProduct.setPrix(12);
		newProduct.setStock(120);

		doNothing().when(produitImp).create(newProduct);

		produitServices.createProduit(newProduct);

		verify(produitImp, times(1)).create(newProduct);
	}




}
