package gestion_commande.models;

import gestion_commande.enums.Role;

public abstract class Utilisateur {

    private Long id;

    private String nom;

    private String prenom;

    private String email;

    private String motDePasse;

    private Role role;
    
    public Utilisateur() {
		// TODO Auto-generated constructor stub
	}
    
    public Long getId() {
		return id;
	}
    
    public String getEmail() {
		return email;
	}
    public String getMotDePasse() {
		return motDePasse;
	}
    public String getNom() {
		return nom;
	}
    public String getPrenom() {
		return prenom;
	}
    public Role getRole() {
		return role;
	}
    public void setEmail(String email) {
		this.email = email;
	}
    public void setId(Long id) {
		this.id = id;
	}
    public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
    public void setNom(String nom) {
		this.nom = nom;
	}
    public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
    public void setRole(Role role) {
		this.role = role;
	}
    
    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }

}
