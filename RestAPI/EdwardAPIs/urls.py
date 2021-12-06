from django.urls import path
from django.urls.resolvers import URLPattern
from . import views


urlpatterns = [
    path('view-foods-users-api/', views.foods_users_api, name='foods-users-api'),
    path('create-users-api/<str:u_name>/<str:u_username>/<str:u_password>/', views.create_user_api, name='create-users-api'),
    path('login-api/<str:u_username>/<str:u_password>/', views.login_api, name='login-api'),
    path('logout-api/', views.logout_api, name='logout-api'),
]
