from django.urls import path
from myDviz import views

urlpatterns = [
    path('center/', views.center, name='center'),
    path('centerLeft/', views.centerLeft, name='centerLeft'),
    path('centerRight/', views.centerRight, name='centerRight'),
    path('centerRightChange/<int:unOUdeuxVoiture>', views.centerRightChange, name='centerRightChange'),

    path('bottomRight/', views.bottomRight, name='bottomRight'),
    path('bottomLeft/', views.bottomLeft, name='bottomLeft'),
]
