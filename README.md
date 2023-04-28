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

### Ajouter ou `finaliser` les contraintes :
- [ ] une voiture ne peut pas être réservée 2 fois le même jour
- [ ] une voiture ne peux pas être réservée plus de 7 jours de suite par le même
  utilisateur (`par edition seulement encore possible`)
- [ ] une voiture ne peut pas être réservé 30 jours de suite sans pause

### Ajout nouvelles fonctionnalités :
- [ ] Mettre des pop-up lorsque les conditions de création ou d'édition ne sont pas remplies
- [ ] Lien hypertexte généralisés sur les IDs, les noms pour aller dans les listes
- [ ] Un lien hypertext sur une adresse mail permet de préremplir un mail
- [ ] Proposer des alertes lorsque la durée de location arrive à son terme
- [ ] Proposer des moyens de paiement