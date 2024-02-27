from django.contrib import admin

# Register your models here.
from .models import Car  # importer model
from .models import Immatriculations
# creer un class pour gerer model
class CarAdmin(admin.ModelAdmin):
    list_display = ('marque', 'nom', 'puissance', 'couleur', 'occasion', 'prix')  # les attributs

class ImmatriculationsAdmin(admin.ModelAdmin):
    list_display_Imma = ('immatriculation','marque', 'nom', 'puissance', 'couleur', 'occasion', 'prix')  # les attributs
# enregister model et classAdmin
admin.site.register(Car, CarAdmin)
admin.site.register(Immatriculations, ImmatriculationsAdmin)
