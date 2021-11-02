from django.urls import path
from django.urls.resolvers import URLPattern
from RestAPI.SagarAPIs.views import create_food_by_user, delete_food_by_user, update_food_by_user, view_foods_by_user, update_user, view_items

urlpatterns = [
    path('create_food_by_user/<str:pk1>/<str:pk2>/<str:pk3>', create_food_by_user, name='create_food_by_user_view'),
    path('delete_food_by_user/<str:pk1>/<str:pk2>/<str:pk3>', delete_food_by_user, name='delete_food_by_user_view'),
    path('update_food_by_user/<str:pk1>/<str:pk2>/<str:pk3>', update_food_by_user, name='update_food_by_user_view'),
    path('view_foods_by_user/<str:pk1>/<str:pk2>', view_foods_by_user, name='view_foods_by_user_view'),
    path('update_user/<str:pk>', update_user, name='update_user_view'),
    path('view_foods/', view_items, name='view_foods'),
]