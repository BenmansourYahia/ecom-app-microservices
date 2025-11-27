# E-Commerce Frontend

Ce projet est une interface Angular minimale pour l'application microservices e-commerce.

## Prérequis

- Node.js
- Angular CLI
- Les microservices (Gateway, Discovery, Config, Customer, Inventory, Billing) doivent être lancés.

## Installation

```bash
npm install
```

## Lancement

Pour lancer l'application en mode développement avec le proxy configuré vers le Gateway (port 8889) :

```bash
npm start
```

L'application sera accessible sur `http://localhost:4200`.

## Architecture

- **Clients** : Affiche la liste des clients via `customer-service`
- **Produits** : Affiche la liste des produits via `inventory-service`
- **Factures** : Affiche la liste des factures via `billing-service`

Toutes les requêtes passent par le proxy configuré dans `proxy.conf.json` qui redirige vers `http://localhost:8889` (Gateway).
