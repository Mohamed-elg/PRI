# PRI

Application web de dématerialisation d'une fiche technique pour l'entreprise BBAI

## Code source

https://github.com/Mohamed-elg/PRI

## Choix Techniques

**Base de données** :
PostgreSQL

**Api Backend** :
Java SpringBoot

**FrontEnd** :
Angular

## Dépendances

- Java17 minimum avec Maven
- NodeJS
- TypeScript et Angular
- Docker

## Lancement

### DEV

Organisation du code avec Maven
Lancement : `mvn spring-boot:run ` ou depuis l'IDE
Api --> http://localhost:8081/api
Documentation api --> http://localhost:8081/api/apidocs

### PROD

Le projet est livré avec Docker (c.f docker-compose.yml)

Pour lancer l'application --> `docker compose up`
Puis sur http://localhost:8082 le compte par défaut est **root**/**root**

**N.B : Ajuster les variables d'environnement et les chemins pour les volumes au besoin**

Api --> http://localhost:8081/api
Documentation api --> http://localhost:8081/api/apidocs

## TODO

- Corriger les bugs de routage Angular
- Hasher les mots de passe et passer sur un système de token dynamique pour les requêtes api
- Pour le stockage des images, voir dans un premier temps le stockage en tant qu'object dans la bdd
- Pour le déploiment, isoler la bdd et la rendre innaccessible depuis l'exterieur (retirer mapping port docker)
- Vérifier les injections SQL de base
