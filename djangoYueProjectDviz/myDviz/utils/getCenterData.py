from django.db.models import Count
from myDviz.models import *
from myDviz.utils.getAllData import getAllCars


def getBaseData():
    client = list(getAllCars()['client'])
    countHighList = {}
    for i in client:
        if countHighList.get(i.taux, -1) == -1:
            countHighList[str(i.taux)] = 1
        else:
            countHighList[str(i.taux)] += 1
    countHighList = [(value, key) for key, value in countHighList.items()]
    countHighList = sorted(countHighList, reverse=True)
    # 1 le plus taux
    # 2 nombre Client du plus taux
    countHighTaux = countHighList[0][0]
    hightaux = countHighList[0][1]

    # countHighList = sorted(countHighList, key=lambda x: x[1], reverse=False)
    # ountHighList = sorted(countHighList, key=lambda x: x[0], reverse=False)

    # 3 Marque Véhicules les plus
    result = Immatriculations.objects.values('marque').annotate(total=Count('marque')).order_by('-total')
    HighVololum = result[0]['marque']
    # 4 Nombre Marque Véhicules les plus
    countHighVololum = result[0]['total']

    # 5 coutEnergie moindres
    min_cout_energie_record = Co2.objects.exclude(coutEnergie='-').order_by('-coutEnergie').last()
    lowCoutEnergie = min_cout_energie_record.coutEnergie
    # 6 La rejetsCO2 qui compte moindre coutEnergie
    lowCoutEnergierejetsCo2 = min_cout_energie_record.rejetsCO2
    euro_index = lowCoutEnergierejetsCo2.find('€')
    lowCoutEnergierejetsCO2 = lowCoutEnergierejetsCo2[:euro_index].strip()
    return hightaux, countHighTaux, HighVololum, countHighVololum, lowCoutEnergie, lowCoutEnergierejetsCO2


def getRollData():
    co2s = list(getAllCars()['co2'])
    co2coutEnergie = {}
    for i in co2s:
        if co2coutEnergie.get(i.coutEnergie, -1) == -1:
            co2coutEnergie[str(i.coutEnergie)] = 1
        else:
            co2coutEnergie[str(i.coutEnergie)] += 1
    co2coutEnergieList = [(value, key) for key, value in co2coutEnergie.items()]
    co2coutEnergieList = sorted(co2coutEnergieList, reverse=True)
    sortDict = {i[1]: i[0] for i in co2coutEnergieList}
    lastsortDict = []
    for k, v in sortDict.items():
        lastsortDict.append({
            'name': k,
            'value': v
        })
    return lastsortDict


def getDeuxiemeVoitureRate():
    clients = getAllCars()['client']
    true_count = clients.filter(deuxiemeVoiture__iexact='true').count()
    false_count = clients.filter(deuxiemeVoiture__iexact='false').count()
    total = true_count + false_count
    # print(set(clients.values_list('sexe', flat=True)))
    return round(true_count / total * 100, 2), round(false_count / total * 100, 2)
