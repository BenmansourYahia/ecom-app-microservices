# E-Commerce Microservices Application

## 📋 Description

Application e-commerce complète basée sur une architecture microservices avec Spring Boot, Spring Cloud, Angular, Kafka, et Keycloak pour l'authentification.

## 🏗️ Architecture

### Microservices Backend

```
┌─────────────────────────────────────────────────────────────┐
│                      API Gateway (8889)                      │
│                    + Keycloak Security                       │
└──────────────────────────┬──────────────────────────────────┘
                           │
        ┌──────────────────┼──────────────────┐
        │                  │                  │
┌───────▼────────┐ ┌──────▼───────┐ ┌────────▼────────┐
│   Customer     │ │  Inventory   │ │    Billing      │
│   Service      │ │   Service    │ │    Service      │
│   (8081)       │ │   (8082)     │ │    (8083)       │
└────────────────┘ └──────────────┘ └─────────────────┘
        │                  │                  │
        └──────────────────┼──────────────────┘
                           │
        ┌──────────────────┼──────────────────┐
        │                  │                  │
┌───────▼────────┐ ┌──────▼───────┐ ┌────────▼────────┐
│   ChatbotAI    │ │    Kafka     │ │   Discovery     │
│   + OpenAI     │ │   Streams    │ │   (Eureka)      │
│   (8087)       │ │   (8086)     │ │    (8761)       │
└────────────────┘ └──────────────┘ └─────────────────┘
```

### Services

| Service | Port | Description | Technologies |
|---------|------|-------------|--------------|
| **Config Service** | 9999 | Configuration centralisée | Spring Cloud Config |
| **Discovery Service** | 8761 | Service registry | Netflix Eureka |
| **Gateway Service** | 8889 | API Gateway + Security | Spring Cloud Gateway, OAuth2 |
| **Customer Service** | 8081 | Gestion des clients | Spring Boot, H2 Database |
| **Inventory Service** | 8082 | Gestion des produits | Spring Boot, H2 Database |
| **Billing Service** | 8083 | Gestion des factures | Spring Boot, H2 Database |
| **ChatbotAI** | 8087 | Assistant IA e-commerce | Spring AI, OpenAI GPT, Telegram Bot |
| **Kafka Service** | 8086 | Event streaming | Kafka Streams, Spring Cloud Stream |
| **Frontend** | 4200 | Interface utilisateur | Angular 19 |
| **Keycloak** | 8080 | Authentification OAuth2 | Keycloak |

---

## 🚀 Technologies Utilisées

### Backend
- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Cloud 2025.0.0**
- **Spring AI 1.1.0-M4**
- **Spring Security OAuth2**
- **Apache Kafka**
- **H2 Database**
- **Lombok**
- **OpenFeign**

### Frontend
- **Angular 19**
- **TypeScript**
- **Keycloak Angular**
- **RxJS**

### Infrastructure
- **Docker & Docker Compose**
- **Keycloak 23.0**
- **Apache Kafka 7.4.0**
- **Zookeeper**

---

## 📦 Fonctionnalités

### ✅ Gestion E-commerce
- CRUD Clients
- CRUD Produits
- Gestion des factures
- Relations entre entités

### ✅ ChatbotAI Intelligent
- **OpenAI GPT Integration** : Assistant conversationnel intelligent
- **Telegram Bot** : Accès via Telegram
- **Feign Clients** : Communication avec tous les microservices
- **AI Tools** :
  - Recherche de produits
  - Informations clients
  - Consultation des factures
- **REST API** : `/chat?query=...`

### ✅ Event-Driven Architecture (Kafka)
- **Producers** : Publication d'événements
  - OrderEvent
  - ProductStockEvent
  - CustomerEvent
- **Consumers** : Traitement des événements
- **Kafka Streams** : Analytics en temps réel
- **REST API** : `/api/events/**`, `/api/analytics`

### ✅ Sécurité (Keycloak)
- **OAuth2 / OpenID Connect**
- **JWT Tokens**
- **Role-Based Access Control (RBAC)**
- **Login centralisé**
- **Protection Gateway**

### ✅ Frontend Angular
- Interface moderne et responsive
- Chatbot UI intégré
- Authentification Keycloak
- Gestion automatique des JWT tokens

---

## 🛠️ Installation et Démarrage

### Prérequis
- Java 21
- Maven 3.9+
- Node.js 18+ & npm
- Docker & Docker Compose
- Git

### 1. Cloner le Projet
```bash
git clone https://github.com/BenmansourYahia/ecom-app-microservices.git
cd ecom-app-microservices
```

### 2. Démarrer l'Infrastructure Docker
```bash
docker-compose up -d
```

Cela démarre :
- Kafka (port 9092)
- Zookeeper (port 2181)
- Keycloak (port 8080)

### 3. Compiler les Services Backend
```bash
mvn clean install -DskipTests
```

### 4. Démarrer les Services (dans l'ordre)

#### 4.1 Config Service
```bash
cd config-service
mvn spring-boot:run
```

#### 4.2 Discovery Service
```bash
cd discovery-service
mvn spring-boot:run
```

#### 4.3 Services Métier
```bash
# Terminal 1
cd customer-service
mvn spring-boot:run

# Terminal 2
cd inventory-service
mvn spring-boot:run

# Terminal 3
cd billing-service
mvn spring-boot:run
```

#### 4.4 Gateway Service
```bash
cd gateway-service
mvn spring-boot:run
```

#### 4.5 ChatbotAI (Optionnel)
```bash
cd ChatbotAI
mvn spring-boot:run
```

#### 4.6 Kafka Service (Optionnel)
```bash
cd kafka-spring-cloud-stream
mvn spring-boot:run
```

### 5. Démarrer le Frontend
```bash
cd ecom-frontend
npm install
npm start
```

---

## 🔐 Configuration Keycloak

### 1. Accéder à Keycloak
- URL : http://localhost:8080
- Login : `admin` / `admin`

### 2. Créer un Realm
1. Cliquer sur **"Create Realm"**
2. Nom : `ecom-realm`
3. Cliquer **"Create"**

### 3. Créer un Client
1. Aller dans **Clients** → **Create client**
2. Client ID : `ecom-frontend`
3. Client type : `OpenID Connect`
4. Cliquer **Next**
5. Client authentication : **OFF**
6. Cliquer **Save**
7. Dans l'onglet **Settings** :
   - Valid redirect URIs : `http://localhost:4200/*`
   - Web origins : `http://localhost:4200`
   - Cliquer **Save**

### 4. Créer des Rôles
1. Aller dans **Realm roles** → **Create role**
2. Créer : `USER` et `ADMIN`

### 5. Créer des Utilisateurs
1. Aller dans **Users** → **Create new user**
2. Username : `user`, Email : `user@test.com`
3. Cliquer **Create**
4. Onglet **Credentials** :
   - Set password : `user`
   - Temporary : **OFF**
   - Cliquer **Save**
5. Onglet **Role mapping** :
   - Assigner le rôle : `USER`

Répéter pour créer `admin` / `admin` avec les rôles `USER` + `ADMIN`

---

## 🧪 Tests

### Accéder aux Services

| Service | URL |
|---------|-----|
| Frontend | http://localhost:4200 |
| Eureka Dashboard | http://localhost:8761 |
| Gateway | http://localhost:8889 |
| Keycloak Admin | http://localhost:8080 |

### API Endpoints (via Gateway)

```bash
# Customers
GET    http://localhost:8889/api/customers
POST   http://localhost:8889/api/customers
GET    http://localhost:8889/api/customers/{id}
PUT    http://localhost:8889/api/customers/{id}
DELETE http://localhost:8889/api/customers/{id}

# Products
GET    http://localhost:8889/api/products
POST   http://localhost:8889/api/products
GET    http://localhost:8889/api/products/{id}

# Bills
GET    http://localhost:8889/api/bills
GET    http://localhost:8889/api/bills/{id}

# ChatbotAI
GET    http://localhost:8889/api/chat?query=hello

# Kafka Events
POST   http://localhost:8889/api/events/order
POST   http://localhost:8889/api/events/stock
POST   http://localhost:8889/api/events/customer
GET    http://localhost:8889/api/analytics
```

### Test avec Authentification

1. Ouvrir http://localhost:4200
2. Vous serez redirigé vers Keycloak
3. Login avec `user` / `user`
4. Vous revenez sur l'application - connecté ! ✅

### Test ChatbotAI

```bash
# Avec JWT token (après login)
curl -H "Authorization: Bearer <your-token>" \
  "http://localhost:8889/api/chat?query=show me all products"
```

### Test Kafka Events

```bash
# Publier un événement Order
curl -X POST http://localhost:8086/api/events/order \
  -H "Content-Type: application/json" \
  -d '{
    "orderId": "ORD-001",
    "customerId": 1,
    "totalAmount": 150.50,
    "status": "CREATED"
  }'

# Consulter les analytics
curl http://localhost:8086/api/analytics
```

---

## 📁 Structure du Projet

```
ecom-ii-bdcc-app/
├── config-service/              # Configuration centralisée
├── discovery-service/           # Eureka Server
├── gateway-service/             # API Gateway + Security
├── customer-service/            # Gestion clients
├── inventory-service/           # Gestion produits
├── billing-service/             # Gestion factures
├── ChatbotAI/                   # Assistant IA
├── kafka-spring-cloud-stream/   # Event streaming
├── ecom-frontend/               # Application Angular
├── config-repo/                 # Fichiers de configuration
│   ├── application.properties
│   ├── customer-service.properties
│   ├── inventory-service.properties
│   ├── billing-service.properties
│   ├── gateway-service.properties
│   ├── chatbot-service.properties
│   └── kafka-service.properties
└── docker-compose.yml           # Infrastructure Docker
```

---

## 🔧 Configuration

### Variables d'Environnement

#### ChatbotAI
Créer `config-repo/chatbot-service-local.properties` :
```properties
spring.ai.openai.api-key=YOUR_OPENAI_API_KEY
telegram.api.key=YOUR_TELEGRAM_BOT_TOKEN
```

### Ports Utilisés

| Port | Service |
|------|---------|
| 2181 | Zookeeper |
| 4200 | Frontend Angular |
| 8080 | Keycloak |
| 8081 | Customer Service |
| 8082 | Inventory Service |
| 8083 | Billing Service |
| 8086 | Kafka Service |
| 8087 | ChatbotAI |
| 8761 | Eureka Discovery |
| 8889 | API Gateway |
| 9092 | Kafka Broker |
| 9999 | Config Service |

---

## 🎓 Concepts Appris

### Architecture Microservices
- ✅ Service Discovery (Eureka)
- ✅ API Gateway
- ✅ Configuration centralisée
- ✅ Load Balancing
- ✅ Circuit Breaker

### Spring Cloud
- ✅ Spring Cloud Config
- ✅ Spring Cloud Gateway
- ✅ Spring Cloud Netflix Eureka
- ✅ Spring Cloud OpenFeign
- ✅ Spring Cloud Stream

### Event-Driven Architecture
- ✅ Apache Kafka
- ✅ Kafka Streams
- ✅ Event Sourcing
- ✅ CQRS patterns

### Sécurité
- ✅ OAuth2 / OpenID Connect
- ✅ JWT Tokens
- ✅ Keycloak
- ✅ RBAC (Role-Based Access Control)

### Intelligence Artificielle
- ✅ Spring AI
- ✅ OpenAI GPT Integration
- ✅ AI Tools & Function Calling
- ✅ Chatbot Development

---

## 📚 Documentation Supplémentaire

- [Guide de démarrage complet](./startup_guide.md)
- [Guide de test](./complete_testing_guide.md)
- [Intégration Keycloak](./keycloak_simple_guide.md)
- [ChatbotAI & Kafka](./chatbot_kafka_fixes.md)

---

## 🐛 Troubleshooting

### Problème : Service ne démarre pas
**Solution** : Vérifier que les services précédents sont démarrés (Config → Eureka → Services)

### Problème : 404 sur les API
**Solution** : Vérifier que le Gateway est démarré et que le service est enregistré dans Eureka

### Problème : ChatbotAI ne répond pas
**Solution** : Vérifier les clés API OpenAI et Telegram dans `chatbot-service-local.properties`

### Problème : Kafka ne se connecte pas
**Solution** : Vérifier que Docker Compose est démarré (`docker-compose up -d`)

### Problème : Erreur CORS
**Solution** : Vérifier la configuration `web origins` dans Keycloak client

---

## 👥 Auteur

**Yahia Benmansour**
- GitHub: [@BenmansourYahia](https://github.com/BenmansourYahia)
- Repository: [ecom-app-microservices](https://github.com/BenmansourYahia/ecom-app-microservices)

---

## 📄 Licence

Ce projet est un projet académique pour l'apprentissage des architectures microservices.

---

## 🙏 Remerciements

- Spring Boot & Spring Cloud
- Netflix OSS
- Apache Kafka
- Keycloak
- OpenAI
- Angular Team

---

**Bon apprentissage ! 🚀**
