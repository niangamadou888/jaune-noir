# Jaune Noir — Backend

Backend Spring Boot de la plateforme de mobilité urbaine **Jaune Noir** (Sénégal).

## Prérequis

- **Java 17** (JDK)
- **Maven 3.8+**
- **PostgreSQL 15+** avec l'extension **PostGIS**

## Installation

```bash
# Cloner le projet
git clone <repo-url>
cd backend

# Créer la base de données PostgreSQL
createdb jaunenoir_dev
psql -d jaunenoir_dev -c "CREATE EXTENSION IF NOT EXISTS postgis;"
```

## Configuration

Les fichiers de configuration se trouvent dans `src/main/resources/` :

| Profil    | Fichier                    | Usage                              |
|-----------|----------------------------|------------------------------------|
| dev       | `application-dev.yml`      | Développement local (PG localhost) |
| staging   | `application-staging.yml`  | Pré-production (env vars)          |
| prod      | `application-prod.yml`     | Production (env vars, ddl=validate)|

## Lancement

```bash
# Compilation
mvn clean compile

# Tests
mvn test

# Lancement en mode dev
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## API Documentation

Une fois l'application lancée, accéder à Swagger UI :

- **Swagger UI** : http://localhost:8080/swagger-ui.html
- **OpenAPI JSON** : http://localhost:8080/v3/api-docs

## Structure du projet

```
src/main/java/com/jaunenoir/
├── domain/          # Entités, enums, exceptions métier
├── application/     # DTOs, mappers, interfaces use cases
├── service/         # Implémentations des services
├── infrastructure/  # Config (Security, CORS, OpenAPI), repositories
├── web/             # Controllers REST, gestion des exceptions
└── shared/          # Constantes et utilitaires partagés
```
