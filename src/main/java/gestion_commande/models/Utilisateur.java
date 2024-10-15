package gestion_commande.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import gestion_commande.enums.Role;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 50, message = "Le nom ne peut pas dépasser 50 caractères")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    @Size(max = 50, message = "Le prénom ne peut pas dépasser 50 caractères")
    private String prenom;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email doit être valide")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(min = 3, message = "Le mot de passe doit contenir au moins 6 caractères")
    private String motDePasse;

    @NotNull(message = "Le rôle est obligatoire")
    @Column(name = "role", nullable = false, columnDefinition = "role")
    @Enumerated(EnumType.STRING)
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
