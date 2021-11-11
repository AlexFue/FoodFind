from django.urls import path
from django.urls.resolvers import URLPattern
from .views import getSavedRecipesOfUser

urlpatterns = [
    path('<userId>/get/', getSavedRecipesOfUser, name='get'),
]