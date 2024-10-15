package gestion_commande.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestiondeCommandes");

    public static void main(String[] args) {
       
        System.out.println("Starting Hibernate...");
        EntityManagerFactory factory = getEntityManagerFactory();

     
        System.out.println("Tables created successfully!");

        
        factory.close();
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
