package gestion_commande.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "produit")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom du produit est obligatoire")
    @Size(max = 100, message = "Le nom du produit ne peut pas dépasser 100 caractères")
    @Column(nullable = false, length = 100)
    private String nom;
    
    @NotBlank(message = "La description est obligatoire")
    @Size(max = 500, message = "La description ne peut pas dépasser 500 caractères")
    @Column(nullable = false, length = 500)
    private String description;

    @NotNull(message = "Le prix est obligatoire")
    @Positive(message = "Le prix doit être positif")
    @Column(nullable = false)
    private Double prix;

    @NotNull(message = "Le stock est obligatoire")
    @PositiveOrZero(message = "Le stock ne peut pas être négatif")
    @Column(nullable = false)
    private Integer stock;
    
    @ManyToMany(mappedBy = "produits")
    private Set<Commande> commande = new HashSet<>();
    

    public Produit() {
    }


    // Getters
    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public double getPrix() {
        return prix;
    }

    public int getStock() {
        return stock;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(double prix) {
        if (prix <= 0) {
            throw new IllegalArgumentException("Le prix doit être supérieur à 0");
        }
        this.prix = prix;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("Le stock ne peut pas être négatif");
        }
        this.stock = stock;
    }

    public Set<Commande> getCommandes() {
        return commande;
    }

    public void setCommandes(Set<Commande> commandes) {
        this.commande = commandes;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", stock=" + stock +
                '}';
    }
}
