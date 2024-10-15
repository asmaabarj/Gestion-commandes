package gestion_commande.interfaces;

import java.util.List;
import java.util.Optional;

public interface GenerInerface<T,ID> {
	
	 void create(T entity);

	    Optional<T> findById(Long id);

	    void update(T entity);

	    void delete(Long id);

	    List<T> getAll();
	    
	    List<T> getPage(int page,int pageSize);
	    
	  		
	    Integer count();
	    

}
