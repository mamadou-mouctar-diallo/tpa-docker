from django.db import models

# Create your models here.
from djongo import models

class Car(models.Model):
    _id = models.ObjectIdField(primary_key=True)
    marque = models.CharField(max_length=100)
    nom = models.CharField(max_length=100)
    puissance = models.IntegerField()
    longueur = models.CharField(max_length=100)
    nbPlaces = models.IntegerField()
    nbPortes = models.IntegerField()
    couleur = models.CharField(max_length=100)
    occasion = models.BooleanField()
    prix = models.CharField(max_length=100)

    class Meta:
        db_table = 'colCatalogue'

class Immatriculations(models.Model):
    _id = models.ObjectIdField(primary_key=True)
    immatriculation=models.CharField(max_length=100)
    marque = models.CharField(max_length=100)
    nom = models.CharField(max_length=100)
    puissance = models.IntegerField()
    longueur = models.CharField(max_length=100)
    nbPlaces = models.IntegerField()
    nbPortes = models.IntegerField()
    couleur = models.CharField(max_length=100)
    occasion = models.BooleanField()
    prix = models.CharField(max_length=100)

    class Meta:
        db_table = 'colImmatriculations'

class Co2(models.Model):
    _id = models.ObjectIdField(primary_key=True)
    modele = models.IntegerField()
    bonusMalus = models.CharField(max_length=100)
    rejetsCO2 = models.CharField(max_length=100)
    coutEnergie = models.CharField(max_length=100)

    class Meta:
        db_table = 'colCo2'

class Clients(models.Model):
    _id = models.ObjectIdField(primary_key=True)
    age = models.IntegerField()
    sexe = models.CharField(max_length=10)
    taux = models.CharField(max_length=5000)
    situationFamiliale = models.CharField(max_length=20)
    nbEnfantsACharge = models.IntegerField()
    deuxiemeVoiture = models.CharField(max_length=100)
    immatriculation = models.CharField(max_length=20)

    class Meta:
        db_table = 'colClients'

class Marketing(models.Model):
    _id = models.ObjectIdField(primary_key=True)
    age = models.IntegerField()
    sexe = models.CharField(max_length=1)
    taux = models.IntegerField()
    situationFamiliale = models.CharField(max_length=20)
    nbEnfantsACharge = models.IntegerField()
    deuxiemeVoiture = models.BooleanField()

    class Meta:
        db_table = 'colMarketing'

class Categories(models.Model):
    _id = models.ObjectIdField(primary_key=True)
    age = models.IntegerField()
    sexe = models.CharField(max_length=12)
    taux = models.IntegerField()
    situationFamiliale = models.CharField(max_length=20)
    nbEnfantsAcharge = models.IntegerField()
    deuxieme_voiture = models.CharField(max_length=20)
    categorie = models.CharField(max_length=20)

    class Meta:
        db_table = 'colCategorieImmatriculations'