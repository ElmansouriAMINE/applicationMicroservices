# applicationMicroservices
## Objectifs de la tâche pratique

L'objectif de cette tâche pratique est d'apprendre les principes de l'architecture des microservices. Plus précisément, nous nous concentrerons sur les aspects suivants :

La création et l'enregistrement de microservices
La connexion à plusieurs bases de données
La mise en place d'un micro-service Gateway
La mise en œuvre d'une communication synchrone entre les microservices

## Architecture des microservices

L'architecture des microservices est une approche de développement d'applications qui consiste à décomposer une application en de petits services indépendants. Chaque microservice est responsable d'une fonctionnalité spécifique et communique avec les autres microservices via des interfaces bien définies.

Dans le cadre de cette tâche pratique, nous utiliserons une architecture des microservices composée des éléments suivants :

Des microservices clients : ces microservices constituent le noyau de l'application et fournissent les fonctionnalités principales.
Une API Gateway : cette API agit comme un point d'entrée centralisé pour l'application, simplifiant la gestion des requêtes.
Un serveur de découverte Eureka : ce serveur permet à chaque microservice de s'enregistrer de manière dynamique, formant ainsi un répertoire décentralisé des services disponibles.
Déroulement de la tâche pratique
[
![archi-Micro](https://github.com/ElmansouriAMINE/applicationMicroservices/assets/101812229/bbb3fe68-6264-477d-a7b6-7dfc2defad18)
](url)

La tâche pratique se déroulera en plusieurs étapes :

### Création et enregistrement de microservices
Dans cette étape, nous créerons deux microservices clients, un microservice Gateway et un serveur de découverte Eureka.

### Connexion à plusieurs bases de données
Dans cette étape, nous configurerons les microservices clients pour qu'ils se connectent à deux bases de données MySQL.

### Mise en place d'un micro-service Gateway
Dans cette étape, nous configurerons l'API Gateway pour qu'elle dirige le trafic vers les microservices clients appropriés.

Mise en œuvre d'une communication synchrone entre les microservices
Dans cette étape, nous mettrons en œuvre une communication synchrone entre les microservices clients à l'aide de l'outil OpenFeign.

## Configuration des fichiers application.properties pour les différentes Microservices:
### Microservice Client:
![client](https://github.com/ElmansouriAMINE/MOROCCO-MONUMENTS-LOCATION-IDENTIFYING/assets/101812229/c45a51c4-96c9-4528-bb0b-1053f1b0fd0b)

### Microservice Voiture:
![voiture](https://github.com/ElmansouriAMINE/MOROCCO-MONUMENTS-LOCATION-IDENTIFYING/assets/101812229/6e4cf374-e784-46cc-ac1e-c3718694f9ba)

### Eureka Server:
![Eureka_server](https://github.com/ElmansouriAMINE/MOROCCO-MONUMENTS-LOCATION-IDENTIFYING/assets/101812229/b140f070-8925-4a59-a7f2-6e917c8a7e38)

### Api GateWay:
![Gateway](https://github.com/ElmansouriAMINE/MOROCCO-MONUMENTS-LOCATION-IDENTIFYING/assets/101812229/b0956306-3672-4109-8d77-cbb6336f6201)





