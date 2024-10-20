package gestion_commande.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import gestion_commande.enums.Statut;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "commande")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_commande", nullable = false)
    private LocalDate dateCommande;

    @NotNull(message = "Statut est obligatoire")
    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false)
    private Statut statut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToMany
    @JoinTable(name = "commande_produit", joinColumns = @JoinColumn(name = "commande_id"), inverseJoinColumns = @JoinColumn(name = "produit_id"))
    private Set<Produit> produits = new HashSet<>();

    public Commande() {
    }

    public Commande(LocalDate dateCommande, Statut statut, Client client) {
        this.dateCommande = dateCommande;
        this.statut = statut;
        this.client = client;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public LocalDate getDateCommande() {
        return dateCommande;
    }

    public Statut getStatut() {
        return statut;
    }

    public Client getClient() {
        return client;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setDateCommande(LocalDate dateCommande) {
        this.dateCommande = dateCommande;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", dateCommande=" + dateCommande +
                ", statut=" + statut +
                ", client=" + client +
                '}';
    }

    public void ajouterProduit(Produit produit) {
        this.produits.add(produit);
        produit.getCommandes().add(this);
    }

    public void retirerProduit(Produit produit) {
        this.produits.remove(produit);
        produit.getCommandes().remove(this);
    }

    public Set<Produit> getProduits() {
        return produits;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }
}
