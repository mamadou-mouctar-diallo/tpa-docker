from myDviz.utils.getAllData import getAllCars
import json


def getCategoriesData():
    categories = list(getAllCars()['categories'])
    # uniqueCategorieslist = set(categorie.categorie for categorie in categories)
    # countCategoriesList={}
    # for i in uniqueCategorieslist:
    #     countCategoriesList[i]=0
    countCategoriesList = {'luxe': 0, 'suv': 0, 'familiale': 0, 'sportive': 0, 'berline compacte': 0, 'citadine': 0}

    for i in categories:
        categorie_value = i.categorie
        if categorie_value == 'luxe':
            countCategoriesList['luxe'] += 1
        elif categorie_value == 'familiale':
            countCategoriesList['familiale'] += 1
        elif categorie_value == 'suv':
            countCategoriesList['suv'] += 1
        elif categorie_value == 'sportive':
            countCategoriesList['sportive'] += 1
        elif categorie_value == 'berline compacte':
            countCategoriesList['berline compacte'] += 1
        else:
            countCategoriesList['citadine'] += 1

    realData = []
    for k,v in countCategoriesList.items():
        realData.append({
            'name':k,
            'value':v
        })
    return realData
