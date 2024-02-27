from myDviz.utils.getAllData import getAllCars
def getSquareData():
    cars = getAllCars()['immatriculations']
    carsVolume = {}
    prixsVolume = {}

    for i in cars:
        if i.prix is not None and i.immatriculation is not None:
            marque = str(i.marque)
            if marque not in carsVolume:
                carsVolume[marque] = 0
                prixsVolume[marque] = 0

            carsVolume[marque] += 1
            prixsVolume[marque] += int(i.prix)

    marquesDict = list(carsVolume.keys())
    carsVolumesDict = list(carsVolume.values())
    prixsVolumesDict = list(prixsVolume.values())

    return marquesDict, prixsVolumesDict, carsVolumesDict
