package gestion_commande.services;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import gestion_commande.models.Produit;
import gestion_commande.repo.ProduitImpl;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;



public class ProduitServices {
	
	
	private ProduitImpl ProduitImpl=new ProduitImpl(); 
	
public ProduitServices() {
	// TODO Auto-generated constructor stub
}



public  void createProduit(Produit entity) {
	ProduitImpl.create(entity);
}

 public   Optional<Produit> findById(Long id){
   	return ProduitImpl.findById(id);

 }

 public  void updateProduit(Produit entity)  {
	   ProduitImpl.update(entity);

   }

  public void deleteProduit(Long id){
	   ProduitImpl.delete(id);

   }

    public List<Produit> getAll(String nom){
    	  List<Produit> p = ProduitImpl.getAll();
    	  if (nom != null && !nom.isEmpty()) {
    	   		return p.stream().filter(produit -> produit.getNom().toLowerCase().contains(nom.toLowerCase())).collect(Collectors.toList());
    	   	}
    	   	return p;
    }
  public List<Produit> getPage(int page,int pageSize){
	  
	  return ProduitImpl.getPage(page, pageSize);
	  
  }
  public Long countProduit() {
	  return ProduitImpl.count();
  }
  

public String getValidationErrors(ConstraintViolationException e) {
    Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
    return violations.stream()
            .map(ConstraintViolation::getMessage)
            .collect(Collectors.joining(", "));
}
}
