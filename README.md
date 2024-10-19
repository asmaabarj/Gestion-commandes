# 📦 Gestion des Commandes en Ligne

Une application web sécurisée pour la gestion des commandes en ligne avec authentification des utilisateurs et interface utilisateur rendue via Thymeleaf. Elle permet aux clients de passer des commandes, et aux administrateurs de gérer les utilisateurs, les produits, et les commandes.

## 🎯 Objectif général de l'application

L'objectif est de fournir une solution complète pour la gestion des commandes en ligne. L'application permet aux clients de commander des produits et aux administrateurs de gérer efficacement les produits, commandes et utilisateurs via une interface conviviale.

## 🛠️ Technologies utilisées

- **Java 8** : Langage principal de développement.
- **Spring MVC** : Architecture de l'application.
- **Thymeleaf** : Moteur de templates pour la génération des vues.
- **PostgreSQL** : Base de données relationnelle.
- **Hibernate/JPA** : ORM pour la gestion de la persistance des données.
- **JUnit & Mockito** : Outils pour les tests unitaires.
- **Maven** : Gestionnaire de dépendances.
- **Tomcat** : Serveur pour déployer l'application.
- **CSS Framework** : Utilisé pour le design des pages.
- **JIRA** : Gestion du projet en utilisant la méthode Scrum.
- **Git** : Gestion du versionnement avec une approche GitFlow.

## 🏗️ Structure du projet

- **Utilisateur** : Gère les informations des utilisateurs (Admin/Client) avec authentification.
- **Produit** : Gère les produits (création, modification, suppression, et affichage).
- **Commande** : Permet la gestion des commandes avec différents statuts (`en attente`, `en traitement`, `expédiée`, `livrée`, `annulée`).
- **Gestion des rôles** : Distinction entre les Admins et les Clients.
- **Tests unitaires** : JUnit et Mockito sont utilisés pour tester les composants métiers.

## 🧩 Architecture adoptée

L'application suit une architecture en couches (MVC - Modèle Vue Contrôleur) :
- **Présentation** : Thymeleaf pour les vues.
- **Métier** : Services contenant la logique métier.
- **Persistance** : Utilisation de JPA/Hibernate pour l'accès aux données.
- **DAO/Repository** : Gestion des accès à la base de données.

## 📦 Instructions d'installation et d'utilisation

### Prérequis
- Java 8
- Maven
- Tomcat 9
- PostgreSQL
- Navigateur web
- Git installé et configuré

### Étapes d'installation

1. **Cloner le projet**
   ```bash
   git clone https://github.com/votre-utilisateur/gestion-commandes.git
   cd gestion-commandes  ```

2. **Configurer la base de données PostgreSQL**

- Créez une base de données nommée gestion_commandes.
- Importez le fichier sql.sql pour générer les tables.
- Modifiez les informations de connexion dans le fichier persistance.xml.
- Construire le projet avec Maven

3. **Construire le projet avec Maven**

 ```bash
mvn clean install 
``` 

4. **Déployer l'application sur Tomcat**

- Copier le fichier .war généré dans le répertoire target/ vers le dossier webapps de Tomcat.
- Démarrer Tomcat.

5. **Accéder à l'application**

Ouvrez votre navigateur et accédez à http://localhost:8080/gestion_commande.


## 🛠️ Configuration de la base de données
PostgreSQL :
Base de données : gestion_commandes
Tables : Client , Admin , Produit, Commande
Scripts SQL inclus dans le dossier src/main/resources/sql/.
Utiliser des index et contraintes (ex. : UNIQUE, NOT NULL).

## 🚀 Lancer l'application sur Tomcat

Démarrer Tomcat à l'aide de la commande suivante :

 ```bash 
<Tomcat_Dir>/bin/startup.sh
``` 

Une fois Tomcat démarré, accédez à l'application via l'URL suivante :

```bash
http://localhost:8080/gestion-commandes 
```

## 📸 Captures d'écran

### Page d'authentification
<img width="417" alt="login_form" src="https://github.com/user-attachments/assets/3ab3beee-b030-4dca-8600-b8478b91bc70">

### Page de gestion des utilisateurs 
<img width="413" alt="Crud_client" src="https://github.com/user-attachments/assets/b8b57d54-d7f7-46a0-838d-53b2b5dfa4e2">
<img width="469" alt="Crud_admin" src="https://github.com/user-attachments/assets/e9ae6516-798d-498b-8d64-b277f742dd7c">
<img width="431" alt="Clients" src="https://github.com/user-attachments/assets/805b022f-c6b4-4e0d-85ec-799491b1ea4f">
<img width="454" alt="Admins" src="https://github.com/user-attachments/assets/1a94ca4c-e181-4999-a7fe-00d89cd2c82f">


### Page de gestion des produits 
![update](https://github.com/user-attachments/assets/3e4e02f0-0022-4e72-b0b5-5684b935115a)
![Produit](https://github.com/user-attachments/assets/c6fa8d88-461c-421a-a9da-5695974718b2)
![Ajouter](https://github.com/user-attachments/assets/5ec14b66-9cec-46d0-be03-9f7d3ce47ee2)

## 💡 Améliorations futures possibles

- Implémentation d'un système de notification par email lors de la validation des commandes.
- Intégration d'une API de paiement en ligne pour finaliser les commandes.
- Optimisation de la gestion des stocks avec des alertes de réapprovisionnement.
- Mise en place d'une interface pour gérer les retours produits.
- Intégration de Docker pour faciliter le déploiement.


## 🏗️ Idées pour étendre le projet

- Ajouter des filtres de recherche plus avancés (par catégorie, prix, etc.).
- Créer un tableau de bord pour les administrateurs avec des statistiques de vente.
- Ajouter un module de gestion des promotions et des réductions.
- Internationaliser l'application avec plusieurs langues disponibles.








