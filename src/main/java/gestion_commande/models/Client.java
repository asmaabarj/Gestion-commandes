package gestion_commande.models;

import gestion_commande.enums.Role;

public class Client extends Utilisateur {

    private String adresseLivraison;
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
