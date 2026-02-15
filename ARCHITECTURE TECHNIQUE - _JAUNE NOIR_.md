## **PROPOSITION D'ARCHITECTURE TECHNIQUE \- "JAUNE NOIR"**

\*Version 1.0 \- Pour discussion et validation collective\*

### **1\. PRINCIPES DIRECTEURS**

* Frugalité : Coûts initiaux minimaux, scaling progressif.  
* Résilience : Fonctionnement acceptable avec connexion intermittente.  
* Compatibilité : Support des smartphones Android anciens (à partir d'Android 8, 2GB RAM).  
* Maintenabilité : Stack connu ou facile à apprendre pour l'équipe.

### **2\. ARCHITECTURE GÉNÉRALE (MVP)**

\[Application Flutter\]  
        ↓

\[API Gateway \- Cloud\]  
        ↓

\[Backend Services \- Node.js\]  
        ↓

\[Base PostgreSQL\]

### 

### 

### 

### 

### **3\. CHOIX TECHNIQUES DÉTAILLÉS**

#### **A. APPLICATION MOBILE \- FRONTEND**

| Choix | Alternative | Avantages | Inconvénients |
| :---- | :---- | :---- | :---- |
| Flutter (Dart) | React Native | • Performance native • Code unique iOS/Android • Taille APK contrôlable (\<15MB) • Supporte Android 8+ | • Courbe d'apprentissage • Écosystème moins mature que React Native |

Fonctionnalités hors-ligne critiques :

* Stockage des courses en cours (SQLite local)  
* Mise en cache des cartes du quartier  
* Synchronisation différée des données

#### **B. BACKEND & API**

| Couche | Technologie Proposée | Justification |
| :---- | :---- | :---- |
| API Gateway | AWS API Gateway / Nginx | Routage, limitation de débit, sécurité |
| Services Principaux | Node.js (Express/Fastify) | • Écosystème riche • Productivité élevée • Courbe d'apprentissage douce |
| Base de Données | PostgreSQL (cloud) | • Fiabilité ACID • Extensions spatiales (géolocalisation) • Scaling vertical/horizontal possible |
| Cache | Redis (pour sessions, tarifs dynamiques) | Performance des lectures fréquentes |

####  

#### **C. INFRASTRUCTURE & DEVOPS**

| Composant | Solution Initiale | Solution de Scale |
| :---- | :---- | :---- |
| Hébergement | AWS Free Tier / Hébergeur local (ex: Datatech) | Kubernetes / AWS ECS |
| CI/CD | GitHub Actions | Jenkins / GitLab CI |
| Monitoring | Sentry (erreurs) \+ logs cloud | Prometheus/Grafana |
| Cartographie | MapLibre GL (OpenStreetMap) | Économie de \>70% vs Google Maps |

### 

### 

### 

### **4\. SCHÉMA DES DONNÉES (CŒUR DU MVP)**

Tables essentielles :

1. **utilisateurs** (chauffeurs, clients)  
2. **courses** (statut, géolocalisation, prix, paiement)  
3. **transactions** (historique financier)  
4. **vehicules** (infos, assurance, documents)

### **5\. SÉCURITÉ & CONFORMITÉ**

* Authentification : JWT \+ refresh tokens  
* Données sensibles : Chiffrement base de données  
* Paiements : Intégration directe avec Wave/Orange Money (pas de stockage CB)  
* RGPD/CNPD : Anonymisation automatique après 6 mois

### **6\. ROADMAP TECHNIQUE (12 SEMAINES)**

| Sprint | Objectif | Livrable |
| :---- | :---- | :---- |
| 1-2 | Environnement dev \+ architecture | API de base fonctionnelle |
| 3-4 | Authentification \+ profils | App mobile avec login |
| 5-6 | Système de géolocalisation | Matching course simple |
| 7-8 | Module de paiement | Transaction cash/mobile money |
| 9-10 | Notifications \+ hors-ligne | App fonctionnelle sans net |
| 11-12 | Tests \+ optimisation | MVP prêt pour beta test |

### **7\. ESTIMATION COÛTS INITIAUX**

| Poste | Coût mensuel (FCFA) | Notes |
| :---- | :---- | :---- |
| Hébergement cloud | 30 000 \- 50 000 | AWS Free Tier les premiers mois |
| Cartographie | GRATUIT | MapLibre \+ OpenStreetMap |
| Noms de domaine | 10 000 / an | .sn \+ .com |
| Outils dev | 0 \- 20 000 | Git, Trello, Figma (gratuit ou freemium) |
| Total mensuel | ≈ 40 000 \- 70 000 |  |

### 

### **8\. POINTS À DÉBATTRE EN RÉUNION**

1. Node.js vs Golang : Performance vs productivité ?  
2. PostgreSQL vs MongoDB : Données relationnelles vs flexibilité ?  
3. AWS vs Hébergeur local : Coût vs latence vs souveraineté ?  
4. Équipe : Qui prend lead sur backend ? Sur mobile ? DevOps ?

**RECOMMANDATION FINALE** :  
Partir sur Flutter \+ Node.js \+ PostgreSQL \+ MapLibre \+ AWS. C'est le meilleur ratio performance/apprentissage/coût pour notre MVP.