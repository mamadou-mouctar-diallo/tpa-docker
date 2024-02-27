from django.http import JsonResponse
from django.shortcuts import render

# Create your views here.
from .models import *
from .utils import getCenterData, getCenterLeftData, getBottomRightData, getBottomLeftData, getCenterRightData, \
    getCenterRightChangeData


def myDviz(request):
    # colCatalogueList=Car.objects.all()
    # return render(request,'myDviz.html',{'colCatalogueList':colCatalogueList})
    try:
        colCatalogueList = Car.objects.all()
        colImmaList = Immatriculations.objects.first()
        colCo2 = Co2.objects.first()
        colClients = Clients.objects.first()
        colMarketing = Marketing.objects.first()
        if not colCatalogueList:
            raise ValueError("No data found in the database.")
    except Exception as e:
        error_message = str(e)  # exception to string
        return render(request, 'error_template.html', {'error_message': error_message})
    return render(request, 'myDviz.html', {'colCatalogueList': colCatalogueList, 'colImmaList': colImmaList,
                                           'colCo2': colCo2, 'colClients': colClients, 'colMarketing': colMarketing})


# def index(request):
#     return render(request,'index.html'

def center(request):
    if request.method == 'GET':
        hightaux, countHighTaux, HighVololum, countHighVololum, lowCoutEnergie, lowCoutEnergierejetsCO2 = getCenterData.getBaseData()
        lastsortDict = getCenterData.getRollData()
        trueRate, falseRate = getCenterData.getDeuxiemeVoitureRate()
        return JsonResponse(
            {
                'hightaux': hightaux,
                'countHighTaux': countHighTaux,
                'HighVololum': HighVololum,
                'countHighVololum': countHighVololum,
                'lowCoutEnergie': lowCoutEnergie,
                'lowCoutEnergierejetsCO2': lowCoutEnergierejetsCO2,
                'lastsortDict': lastsortDict,
                'trueRate': trueRate,
                'falseRate': falseRate
            }
        )


def centerLeft(request):
    if request.method == 'GET':
        lastPieDict = getCenterLeftData.getPieMarqueData()
        return JsonResponse(
            {
                'lastPieDict': lastPieDict,
            }
        )


def centerRight(request):
    if request.method == 'GET':
        countCategoriesList = getCenterRightData.getCategoriesData()
        return JsonResponse(
            {
                'countCategoriesList': countCategoriesList
            }
        )


def centerRightChange(request, unOUdeuxVoiture):
    if request.method == 'GET':
        uneVoiture, deuxVoiture = getCenterRightChangeData.getCircleData()
        realData = []
        if unOUdeuxVoiture == 1:
            realData = deuxVoiture
        else:
            realData = uneVoiture
        return JsonResponse(
            {
                'realData': realData
            }
        )


def bottomRight(request):
    if request.method == 'GET':
        tree_data = getBottomRightData.getTreeData()
        return JsonResponse(tree_data, safe=False)


def bottomLeft(request):
    if request.method == 'GET':
        marquesDict, prixsVolumesDict, carsVolumesDict = getBottomLeftData.getSquareData()
        return JsonResponse(
            {
                'marquesDict': marquesDict,
                'prixsVolumesDict': prixsVolumesDict,
                'carsVolumesDict': carsVolumesDict
            }
        )
