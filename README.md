# tpa-docker
Ce repository contient toutes les configurations des outils que nous avons besoin pour bien mener le projet tpa

# Docker-Config-BigData

Ce référentiel contient une configuration Docker prête à l'emploi pour déployer des conteneurs des principaux outils de traitement de données, offrant une solution Big Data complète et évolutive.

## Outils inclus

- **MongoDB**: Base de données NoSQL flexible et évolutive.
- **Spark**: Moteur de traitement de données rapide et polyvalent.
- **Hive**: Infrastructure de data warehouse basée sur Hadoop pour l'analyse et la requête de données.
- **Cassandra**: Base de données distribuée hautement évolutive.
- **PostgreSQL**: Système de gestion de base de données relationnelle puissant.
- **Adminer**: Interface utilisateur web pour la gestion de bases de données (compatible avec MongoDB, MySQL, PostgreSQL, etc.).
- **Mongo Express**: Interface web pour la gestion de bases de données MongoDB.

## Utilisation

1. Assurez-vous que Docker et Docker Compose sont installés sur votre machine.
2. Clonez ce référentiel sur votre machine locale - **https://github.com/mamadou-mouctar-diallo/tpa-docker.git**.
3. Naviguez vers le répertoire du projet.
4. Exécutez la commande `docker-compose up -d`.
5. Accédez aux différents services via les ports exposés.

## Ports exposés

- **MongoDB**: [localhost:27017](http://localhost:27017)
- **Adminer**: [localhost:8080](http://localhost:8080)
- **Spark Master**: [localhost:9090](http://localhost:9090)
- **Jupyter Notebook (avec Spark)**: [localhost:8888](http://localhost:8888)
- **Hive Web UI**: [localhost:10002](http://localhost:10002)
- **Cassandra CQLSH**: [localhost:9042](http://localhost:9042)
- **PostgreSQL**: [localhost:5432](http://localhost:5432)
- **Mongo Express**: [localhost:8081](http://localhost:8081)

## Personnalisation

Vous pouvez personnaliser la configuration en modifiant le fichier `docker-compose.yml` selon vos besoins spécifiques.

---

*Note: Assurez-vous que les ports spécifiés ne sont pas utilisés par d'autres services sur votre machine.*

