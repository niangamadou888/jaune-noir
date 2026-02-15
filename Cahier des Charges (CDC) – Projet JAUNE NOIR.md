# **Cahier des Charges (CDC) – Projet JAUNE NOIR**

**Plateforme sénégalaise de mobilité urbaine**  
 Document de référence stratégique et technique – **Version 1.1**

## **1\. Présentation du projet**

### **1.1 Contexte**

Le projet *Jaune Noir* vise à corriger des déséquilibres observés dans le secteur du transport urbain au Sénégal : commissions élevées prélevées par certaines plateformes étrangères et faible prise en compte des réalités locales. Il s’inscrit dans un contexte favorable où l’État soutient la structuration du numérique et les initiatives locales à fort impact social.

**Constats majeurs à l’origine du projet :**

* perte significative de revenus pour les chauffeurs de taxi ;

* hausse artificielle des tarifs pour les passagers ;

* dépendance technologique vis-à-vis d’acteurs non locaux.

### 

### 

### **1.2 Objectif général**

Concevoir et déployer une plateforme numérique de transport urbain performante, sécurisée et évolutive, détenue et opérée localement, capable de concurrencer des solutions établies à grande échelle.

**Axes de déclinaison :**

* **Économique :** proposer un modèle équitable pour les chauffeurs.

* **Technique :** construire une plateforme robuste, scalable et sécurisée.

* **Institutionnel :** respecter la réglementation et favoriser la souveraineté numérique.

## **2\. Public cible**

Le projet s’adresse prioritairement aux acteurs directs du transport urbain et aux usagers, tout en impliquant les parties prenantes institutionnelles nécessaires au déploiement.

**Publics identifiés :**

* Chauffeurs de taxi urbains (indépendants ou membres de coopératives) ;

* Passagers urbains, utilisateurs réguliers ou occasionnels ;

* Coopératives, syndicats et associations de taxi ;

* Institutions de régulation et partenaires publics ;

* Équipe interne (technique, juridique, opérationnelle).

## **3\. Périmètre du projet**

La phase 1 vise le développement et le lancement d’un **MVP** fonctionnel, limité à la zone de Dakar pour maîtriser risques et coûts et valider le modèle.

### **3.1 Inclus dans le périmètre**

* application mobile **Passager** ;

* application mobile **Chauffeur** ;

* plateforme web d’**administration / supervision** ;

* backend centralisé exposant des **API sécurisées** ;

* mise en conformité juridique et réglementaire initiale.

### **3.2 Hors périmètre (Phase 1\)**

* paiements par **carte bancaire** ;

* courses inter-urbaines ;

* services de **livraison** ;

* expansion géographique hors Dakar puis vers la sous-région.

## **4\. Description fonctionnelle**

Les fonctionnalités sont organisées par modules pour assurer clarté et évolutivité.

### **4.1 Gestion des utilisateurs**

Ce module couvre l’administration des comptes et des accès :

* inscription et authentification par **OTP** ;

* gestion des profils et rôles (client, chauffeur, admin) ;

* suspension, vérification documentaire et historisation des comptes.

### **4.2 Application Passager**

Conçue pour la simplicité, la transparence et l’accessibilité :

* estimation du prix avant confirmation ;

* recherche et suivi de course en temps réel ;

* Choix du type de véhicule ;

* paiement : **cash** et **mobile money** ;

* notation et retours d’expérience ;,

* historique des courses et reçus numériques, Formulaire de contact pour le support client (problème de course, objet oublié) ;

* centre d’aide / FAQ intégré.

### **4.3 Application Chauffeur**

Outil opérationnel destiné aux chauffeurs partenaires :

* inscription professionnelle avec vérification des documents (pièce d’identité, permis, carte grise, assurance) ;

* statut « Disponible / Indisponible » ;

* notifications sonores/visuelles pour nouvelles courses ;

* Affichage des informations de la course : point de départ, destination estimée, prix fixe pour le chauffeur (après commission)

* acceptation/refus avec pénalité maîtrisé ;

* Navigation intégrée (via Google Maps/MapLibre) vers le point de prise en charge puis la destination ;

* tableau de bord financier (gains, historique, suivi de la commission journalière plafonnée) ;

* Notation du passager après chaque course ;

* accès au support dédié et aux annonces officielles ;

* Profil public visible des passagers (note moyenne, nombre de courses)

* Créditer son compte via Wave / Orange Money pour le paiement des commissions.

* Marquer le passager comme "pris en charge"

### **4.4 Espace Administrateur (Dashboard)**

Supervision et pilotage opérationnel :

* KPI en temps réel (courses, CA, chauffeurs/passagers actifs) ;

* Graphiques d'activité (courses par jour, revenus par période)

* gestion des utilisateurs et validation documentaire ;

* supervision en temps réel des courses (filtrage, détail, modération) ;

* outils de gestion financière et génération de rapports (journaliers, hebdomadaires, mensuels) ;

* Vue des transactions de paiement

* interface de ticketing pour support et litiges ;

* outils d’audit et d’export.

## **5\. Exigences techniques et architecture**

### **5.1 Architecture logicielle**

La plateforme adopte une **architecture en couches** conforme aux bonnes pratiques d’ingénierie :

**Principes :**

* **Clean Architecture** ;

* séparation stricte : *domain*, *application*, *infrastructure*, *présentation* ;

* exposition des services via **API REST** documentées.

### **5.2 Stack technique cible**

Choix retenus pour stabilité, maintenabilité et montée en charge :

* **Mobile :** Flutter ;

* **Web :** Angular ;

* **Backend :** Spring Boot ;

* **Base de données :** PostgreSQL (avec extensions spatiales PostGIS) ;

* **CI/CD :** GitHub Actions (ou équivalent).

## **6\. Exigences non fonctionnelles**

### **6.1 Performance et scalabilité**

La plateforme doit pouvoir évoluer sans refonte majeure :

* optimisation des temps de réponse ;

* capacité de montée en charge (scaling horizontal) ;

* tolérance aux pannes et reprise.

### **6.2 Sécurité**

La sécurité est critique dès le MVP :

* chiffrement des données sensibles au repos et en transit ;

* gestion fine des rôles et permissions (**RBAC**) ;

* protection contre XSS, CSRF et injections SQL ;

* journalisation des actions sensibles (audit log) ;

* Parcours Sécurité : Intégrer un centre de sécurité avec des *checklists* personnalisées pour guider les utilisateurs (ajout contact, partage d'itinéraire)

* Vérification PIN : Mettre en place un code PIN à 4 chiffres, généré pour chaque course, que le passager doit communiquer au chauffeur pour lancer le trajet

* conformité aux exigences CNPD et aux bonnes pratiques de protection des données.

### **6.3 Qualité logicielle**

Exigences de développement et maintenance :

* tests unitaires et d’intégration systématiques ;

* documentation technique continue ;

* revues de code et CI automatisée ;

* gestion de versions et politique de branches (gitflow ou équivalent).

## **7\. Livrables attendus**

Les livrables couvrent les aspects produit, technique et documentation :

* applications mobiles (APK / publication Play Store) et plateforme web ;

* diagrammes UML : cas d’usage, classes, séquences, state machine ;

* Charte graphique complète et support numériques pour les RS, marketing et présence numérique suivant les événements au Sénégal ;

* maquettes Figma (low & hi-fidelity) ;

* documentation API (Swagger / Postman) ;

* dépôts Git structurés avec historique et CI/CD ;

* documentation opérationnelle (runbooks, playbooks de sécurité).

## **8\. Déploiement et exploitation**

**Environnements :** dev / staging / production.

 **Exigences opérationnelles :**

* sauvegardes régulières et plan de restauration ;

* monitoring et alerting (logs, métriques, traces) ;

* procédures de mise à jour et rollback ;

* gestion des secrets via KMS/Vault ;

* plan de gestion des incidents et communication.

## **9\. Conclusion**

Le présent cahier des charges (Version 1.1) constitue le cadre de référence structurant pour *Jaune Noir*. Il fixe les bases fonctionnelles, techniques et organisationnelles nécessaires à la construction d’une plateforme de mobilité crédible, sécurisée et évolutive. Ce document vise à garantir la cohérence des choix futurs : toute évolution majeure devra être formalisée par avenant et validée par l’équipe fondatrice afin de préserver la vision, la qualité et la capacité de *Jaune Noir* à s’imposer durablement comme une solution sénégalaise de référence.

