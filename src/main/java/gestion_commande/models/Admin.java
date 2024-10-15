package gestion_commande.models;

import gestion_commande.enums.Role;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Admin extends Utilisateur {

    @NotNull(message = "Le niveau d'accès ne peut pas être nul")
    private Integer niveauAcces;

    public Admin() {
        super();
        setRole(Role.Admin);
    }

    public Integer getNiveauAcces() {
        return niveauAcces;
    }

    public void setNiveauAcces(Integer niveauAcces) {
        this.niveauAcces = niveauAcces;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + getId() +
                ", nom='" + getNom() + '\'' +
                ", prenom='" + getPrenom() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", role=" + getRole() +
                ", niveauAcces=" + niveauAcces +
                '}';
    }
}
