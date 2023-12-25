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

## Documentation Sur ETL (Extract Transform Load)

### 1. Chager les données dans MongoDB
Pour charger les donnees daans MongoDB, il faut d'abord lancer le service MongoDB avec la commande suivante:
```bash docker-compose up -d mongodb```
Ensuite, il faut lancer le service mongo-express avec la commande suivante:
```bash docker-compose up -d mongo-express```
Enfin, il faut pull la mise a jour du repository: tpa-docker avec la commande suivante:
```bash git pull origin master```
Vous verrez le repertoire concessionnaire qui contient le programme java qui permet de charger les donnees dans le serveur MongoDB.
Si vous avez votre variable d'environnement JAVA_HOME, vous pouvez lancer le programme java avec la commande suivante:

```mvn clean install```

```mvn exec:java -Dexec.mainClass="tpa.MongdbMain"```
Sinon avec intelij vous pouvez lancer le programme java en choisissant la classe MongdbMain.java et cliquer sur le bouton run.

NB: A votre place je ne ferai pas ca si jamais ma machine n'est pas assez puissante pour supporter tous les services en meme temps, car le chargement prend beaucoup de temps voir des heures du coup a eviter.

### 2. Solution alternative pour charger les donnees dans MongoDB
J'ai generee des fichiers json a partir des donnees csv, et j'ai utilise le service mongo-express pour charger les donnees dans MongoDB. Du coup, vous trouverez dans le repertoire tpa-docker les fichiers json de toute la base de donnees concessionnaire.

Que vous ayez lance le service MongoBD dans un conteneur docker ou sur votre machine locale, vous pouvez charger les donnees dans MongoDB avec la commande suivante:

```bash mongoimport --db concessionnaire --collection marketing --file marketing.json```

```bash mongoimport --db concessionnaire --collection co2 --file co2.json```

```bash mongoimport --db concessionnaire --collection clients --file clients.json```

```bash mongoimport --db concessionnaire --collection immatriculations --file immatriculations.json```

```bash mongoimport --db concessionnaire --collection catalogue --file catalogue.json```

## Echange de donnees entre MongoDB, OracleNoSql et Hive
### 1. Echange de donnees entre MongoDB et Hive
Comin soon
### 2. Echange de donnees entre OracleNoSql et Hive
Comin soon

Normalement avec les donnees disponibles dans MongoDB, vous pouvez faire votre travail.
