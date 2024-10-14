package gestion_commande.models;

import java.time.LocalDate;

import gestion_commande.enums.Statut;

public class Commande {

    private Long id;
    private LocalDate dateCommande;
    private Statut statut;
    private Long clientId;

    public Commande() {
        // Constructeur par défaut
    }

    public Commande(LocalDate dateCommande, Statut statut, Long clientId) {
        this.dateCommande = dateCommande;
        this.statut = statut;
        this.clientId = clientId;
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

    public Long getClientId() {
        return clientId;
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

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", dateCommande=" + dateCommande +
                ", statut=" + statut +
                ", clientId=" + clientId +
                '}';
    }
}
