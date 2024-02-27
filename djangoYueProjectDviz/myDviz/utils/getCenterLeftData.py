from myDviz.utils.getAllData import getAllCars

def getPieMarqueData():
    marques = list(getAllCars()['catalogue'])
    marquesVololume = {}
    for i in marques:
        if marquesVololume.get(i.marque, -1) == -1:
            marquesVololume[str(i.marque)] = 1
        else:
            marquesVololume[str(i.marque)] += 1
    marquesVololume = sorted(zip(marquesVololume.values(), marquesVololume.keys()), reverse=True)

    sortDict = {i[1]: i[0] for i in marquesVololume}
    lastPieDict = []
    for k, v in sortDict.items():
        lastPieDict.append({
            'name': k,
            'value': v
        })
    # print(lastPieDict)
    return lastPieDict
