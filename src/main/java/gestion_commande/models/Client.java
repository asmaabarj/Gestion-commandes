package gestion_commande.models;

import gestion_commande.enums.Role;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Client extends Utilisateur {

    @NotBlank(message = "L'adresse de livraison ne peut pas être vide")
    private String adresseLivraison;

    @NotBlank(message = "Le moyen de paiement ne peut pas être vide")
    private String moyenPaiement;

    public Client() {
        super();
        setRole(Role.Client); 
    }


    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    public String getMoyenPaiement() {
        return moyenPaiement;
    }

    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    public void setMoyenPaiement(String moyenPaiement) {
        this.moyenPaiement = moyenPaiement;
    }

    @Override
    public String toString() {
        return super.toString() + ", Client{" +
                "adresseLivraison='" + adresseLivraison + '\'' +
                ", moyenPaiement='" + moyenPaiement + '\'' +
                ", role=" + getRole() +

                '}';
    }
}
