from django.urls import path
from django.urls.resolvers import URLPattern
from . import views


urlpatterns = [
    path('view-foods-users-api/', views.foods_users_api, name='foods-users-api'),
]
