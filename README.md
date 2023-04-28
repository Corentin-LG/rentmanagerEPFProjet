# Rent Manager
Projet pour apprendre le Java avec les méthodes passées et actuelles
## Installation

### 1. Enlever les anciennes bases de données présentes
Aller dans `C:\Users\[nomUtilisateur]`
Supprimer `RentManagerDatabase.mv.db` et `RentManagerDatabase.trace.db`

### 2. Maven
Dans l'onglet Maven, chercher la terminal et faire dans l'ordre
```Terminal
mvn clean
mvn install
mvn compile
mvn tomcat7:run
```
### 3. Utiliser l'appli locale
Elle est accessible avec l'url :
http://localhost:8080/rentmanager

### 4. Utiliser l'UI
Arrêter l'application activée depuis l'environnement de développement
Lancer `UIMain`

### 5. Tester les classes
Des tests sont déjà disponibles dans `UserServiceTest`

## Idées d'amélioration

- [ ] Fonctionnalité delete non opérationnelle
- [ ] Ajouter les contraintes :
- une voiture ne peut pas être réservée 2 fois le même jour
- une voiture ne peux pas être réservée plus de 7 jours de suite par le même
  utilisateur
- une voiture ne peut pas être réservé 30 jours de suite sans pause
- si un client ou un véhicule est supprimé, alors il faut supprimer les
  réservations associées
- une voiture doit avoir un modèle et un constructeur, son nombre de place doit
  être compris entre 2 et 