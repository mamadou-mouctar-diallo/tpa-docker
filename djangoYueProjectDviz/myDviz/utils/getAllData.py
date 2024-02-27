from myDviz.models import *

def getAllCars():
    catalogue=Car.objects.all()
    client=Clients.objects.all()
    co2 = Co2.objects.all()
    immatriculations = Immatriculations.objects.all()
    marketing = Marketing.objects.all()
    categories = Categories.objects.all()
    return {
        'catalogue': catalogue,
        'client': client,
        'co2': co2,
        'immatriculations': immatriculations,
        'marketing': marketing,
        'categories':categories
    }
