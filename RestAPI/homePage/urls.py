from django.urls import path
from django.urls.resolvers import URLPattern
from . import views
urlpatterns = [
    path('<userId>/<recipeId>/add/', views.addFoodToSavedList, name='add'),
]