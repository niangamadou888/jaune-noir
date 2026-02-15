# **DOCUMENT D’ARCHITECTURE TECHNIQUE (DAT)**

# **Architecture Détaillée du Système**

## **1\. Introduction**

### **1.1 Objectif du document**

Le présent **Document d’Architecture Technique (DAT)** décrit l’architecture globale et détaillée du système. Il sert de référence officielle pour la conception, le développement, le déploiement et l’évolution du projet.

### **1.2 Approche architecturale**

L’architecture du système s’appuie sur les principes suivants :

* **Architecture en couches** ;  
* **Clean Architecture** ;  
* **Principes SOLID** ;  
* **Domain-Driven Design (DDD)**.

Cette approche garantit la maintenabilité, la testabilité, l’évolutivité et l’indépendance vis-à-vis des frameworks.

---

## **2\. Vue d’ensemble de l’architecture**

Le système est structuré autour de plusieurs couches clairement séparées, chacune ayant des responsabilités bien définies. Les dépendances sont orientées vers le cœur métier, conformément aux principes de la Clean Architecture.

Les couches principales sont :

1. Couche Domaine (Domain Layer)  
2. Couche Application (Application Layer)  
3. Couche Infrastructure (Infrastructure Layer)  
4. Couche Service  
5. Couche Web (API REST)  
6. Composants partagés (Shared Layer)

---

## **3\. Description détaillée des couches**

## **3.1 Couche Domaine (Domain Layer)**

### **Rôle**

La couche Domaine constitue le cœur du système. Elle contient la logique métier pure, indépendante de toute considération technique.

### **Responsabilités**

* Définition des entités métier ;  
* Implémentation des règles métier ;  
* Définition des exceptions métier ;  
* Définition des valeurs métier immuables.

### **Composants**

* **Entités** : objets métier avec identité et cycle de vie ;  
* **Énumérations** : valeurs métier fixes ;  
* **Exceptions métier** : erreurs liées aux règles du domaine.

Caractéristiques :

* Aucune dépendance vers les frameworks ;  
* Logique métier centralisée ;  
* Forte cohésion.

---

## **3.2 Couche Application (Application Layer)**

### **Rôle**

La couche Application orchestre les cas d’utilisation du système et coordonne les interactions entre le domaine et les couches externes.

### **Responsabilités**

* Définition des cas d’utilisation (Use Cases) ;  
* Transformation des données via des DTOs ;  
* Validation des règles métier applicatives ;  
* Coordination des transactions.

### **Composants**

* **DTOs (Request / Response)** ;  
* **Interfaces de services (Use Cases)** ;  
* **Mappers** pour la conversion entités ↔ DTOs ;  
* **Validators** pour les règles métier complexes.

---

## **3.3 Couche Service**

### **Rôle**

La couche Service implémente les cas d’utilisation définis dans la couche Application.

### **Responsabilités**

* Implémentation de la logique applicative ;  
* Coordination des appels aux repositories ;  
* Gestion des transactions ;  
* Application des validations métier.

Cette couche constitue le point central de l’exécution des processus métier.

---

## **3.4 Couche Infrastructure (Infrastructure Layer)**

### **Rôle**

La couche Infrastructure gère les détails techniques et l’accès aux ressources externes.

### **Responsabilités**

* Accès à la base de données ;  
* Implémentation des repositories ;  
* Configuration technique ;  
* Intégration de services externes ;  
* Sécurité et aspects transverses.

### **Technologies**

* **Backend** : Spring Boot ;  
* **ORM** : Spring Data JPA ;  
* **Base de données** : PostgreSQL.

---

## **3.5 Couche Web (API REST)**

### **Rôle**

La couche Web constitue le point d’entrée du système et expose les fonctionnalités via des API REST.

### **Responsabilités**

* Exposition des endpoints REST ;  
* Gestion des requêtes et réponses HTTP ;  
* Validation des entrées utilisateur ;  
* Gestion centralisée des exceptions ;  
* Documentation de l’API.

### **Clients**

* **Application Web** : Angular ;  
* **Application Mobile** : Flutter.

---

## **3.6 Composants partagés (Shared Layer)**

### **Rôle**

Cette couche regroupe les éléments communs utilisés par plusieurs couches.

### **Composants**

* Constantes globales ;  
* Classes utilitaires ;  
* Objets communs.

---

## **4\. Communication et flux de données**

* Les applications Web et Mobile communiquent avec le backend via des **API REST** ;  
* Les échanges de données se font au format **JSON** ;  
* Les transactions sont gérées au niveau de la couche Service ;  
* La base de données est accessible uniquement via la couche Infrastructure.

---

## **5\. Sécurité**

Les principes de sécurité appliqués sont :

* Validation des entrées utilisateur ;  
* Gestion des accès et des rôles ;  
* Journalisation des actions critiques ;  
* Audit des données (création, modification) ;  
* **Authentification par OTP (One-Time Password)**.

### **5.1 Authentification par OTP**

L’authentification repose sur l’utilisation de **codes OTP temporaires**, envoyés à l’utilisateur via un canal sécurisé (SMS, e-mail ou application dédiée).

Principes retenus :

* Génération d’un code OTP à durée de validité limitée ;  
* Association du code OTP à un utilisateur et à une session ;  
* Vérification du code OTP côté serveur ;  
* Possibilité d’évolution vers une authentification multi-facteurs (MFA).

Cette approche renforce la sécurité tout en simplifiant l’expérience utilisateur.

---

## **6\. Performance et évolutivité**

### **Performance**

* Pagination systématique des listes ;  
* Chargement paresseux des relations ;  
* Optimisation des requêtes ;  
* Possibilité d’intégrer un mécanisme de cache.

### **Évolutivité**

* Ajout de nouvelles entités sans impact majeur ;  
* Ajout de nouvelles fonctionnalités par extension des cas d’utilisation ;  
* Architecture modulaire facilitant la montée en charge.

---

## **7\. Hébergement et déploiement**

### **Environnements**

* **Développement** : serveurs en ligne gratuits ;  
* **Production** : serveurs cloud.

### **Solutions envisagées**

* Render ;  
* Services cloud (AWS, Azure).

---

## **8\. Monitoring, tests et documentation**

### **Monitoring**

* Exposition d’indicateurs de santé ;  
* Journalisation centralisée ;  
* Suivi des performances.

### **Tests**

* Tests unitaires ;  
* Tests d’intégration ;  
* Tests end-to-end.

### **Documentation**

* Documentation technique (présent DAT) ;  
* Documentation API (Swagger) ;  
* Guides d’utilisation.

---

## **9\. Schéma d’architecture globale (prêt pour draw.io / Lucidchart)**

### **9.1 Schéma d’architecture logique (vue applicative)**

Le schéma suivant peut être reproduit directement dans **draw.io / Lucidchart** sous forme de blocs et de flèches.

\[ Utilisateurs \]  
|  
v  
\+-----------------------+		\+---------------------------+  
| Application Web  |		| Application Mobile    |  
| Angular                |		| Flutter                       |  
\+-----------------------+		\+---------------------------+  
               |                                             |  
   \+----------------+------------------+  
                                    v  
\+----------------------------+  
| API REST                  |  
| Spring Boot               |  
| (Couche Web)           |  
\+----------------------------+  
  |  
  v  
\+--------------------------+  
 		| Couche Service     |  
| Logique applic.      |  
\+-------------------------+  
                                     |  
                                      v  
           	\+-------------------------+                       
| Couche Domaine   |  
| Entités / Règles     |  
\+------------------------+  
  |  
 v  
\+------------------------+  
| Couche Infra.        |  
| Repositories JPA  |  
\+------------------------+  
  |  
 v  
\+-------------------------+  
| PostgreSQL           |  
| Base de données   |  
\+-------------------------+

### **9.2 Flux principaux**

* Les clients Web et Mobile consomment l’API REST via HTTPS ;  
* Les requêtes sont traitées par la couche Web puis la couche Service ;  
* Les règles métier sont appliquées dans la couche Domaine ;  
* L’accès aux données est effectué via la couche Infrastructure ;  
* Les réponses sont retournées au format JSON.

---

## **10\. Architecture de déploiement et CI/CD**

### **10.1 Environnements**

Le système est déployé selon plusieurs environnements distincts :

* **Développement (DEV)**  
  * Environnements locaux et serveurs en ligne gratuits ;  
  * Utilisés pour le développement et les tests initiaux.  
* **Pré-production / Test (STAGING)** *(optionnel)*  
  * Validation fonctionnelle et technique ;  
  * Tests d’intégration et de performance.  
* **Production (PROD)**  
  * Hébergement sur serveurs cloud (AWS / Azure / Render) ;  
  * Accès utilisateurs finaux.

### **10.2 Architecture de déploiement**

\[ Développeur \]  
|  
v  
\[ Git Repository \] (GitHub / GitLab)  
|	  
v  
\[ CI Pipeline \]  
\- Build  
\- Tests  
\- Analyse qualité  
|  
v  
\[ CD Pipeline \]  
\- Packaging (Docker)  
\- Déploiement automatique  
|  
v  
\+-----------------------------------+  
|  Environnement Cloud       |  
|  \- Backend Spring Boot     |  
|  \- Base PostgreSQL          |  
|  \- Frontend Angular           |  
|  \- Monitoring & Logs          |  
\+----------------------------------+

### **10.3 Pipeline CI/CD (principe)**

1. **Commit du code** dans le dépôt Git ;  
2. Déclenchement automatique du pipeline CI ;  
3. Exécution des tests unitaires et d’intégration ;  
4. Build de l’application ;  
5. Création des artefacts (images Docker) ;  
6. Déploiement automatique vers l’environnement cible ;  
7. Vérification post-déploiement.

### **10.4 Bonnes pratiques de déploiement**

* Séparation stricte des environnements ;  
* Variables de configuration externalisées ;  
* Logs centralisés ;  
* Possibilité de rollback rapide ;  
* Supervision et alertes.

---

## **11\. Livrables**

* **Schéma d’architecture globale du système** ;  
* **Schéma d’architecture de déploiement** ;  
* **Document d’Architecture Technique (DAT)**.

---

## **12\. Conclusion**

Cette architecture fournit une base solide, maintenable et évolutive pour le système. Elle respecte les bonnes pratiques de conception logicielle et permet une évolution maîtrisée du projet.

