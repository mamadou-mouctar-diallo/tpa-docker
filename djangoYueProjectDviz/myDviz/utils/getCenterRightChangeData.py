from myDviz.utils.getAllData import getAllCars


def getCircleData():
    cars = list(getAllCars()['categories'])
    oneCar = []
    twoCar = []
    for i in cars:
        if i.deuxieme_voiture.upper() == 'FAUX':
            oneCar.append([i.taux,i.age, i.sexe,  i.categorie])
        elif i.deuxieme_voiture.upper() == 'VRAI':
            twoCar.append([i.taux, i.age, i.sexe, i.categorie])

    oneCar = sorted(oneCar, key=lambda x: x[1])[:10]
    twoCar = sorted(twoCar, key=lambda x: x[1])[:10]
    return oneCar, twoCar
