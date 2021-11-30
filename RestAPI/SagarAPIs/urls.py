from django.urls import path
from django.urls.resolvers import URLPattern
from RestAPI.SagarAPIs.views import *

urlpatterns = [
    path('create_food/<str:pk>/', create_food, name='create_food_view'),
    path('create_food_by_user/<str:pk1>/<str:pk2>/<str:pk3>', create_food_by_user, name='create_food_by_user_view'),
    path('delete_food_by_user/<str:pk1>/<str:pk2>/<str:pk3>', delete_food_by_user, name='delete_food_by_user_view'),
    path('update_food_by_user/<str:pk1>/<str:pk2>/<str:pk3>', update_food_by_user, name='update_food_by_user_view'),
    path('view_foods_by_user/<str:pk1>/<str:pk2>', view_foods_by_user, name='view_foods_by_user_view'),
    path('update_user/<str:pk>', update_user, name='update_user_view'),
    path('view_recipes/', view_recipes, name='view_foods'),
    path('view_recipe_by_id/<str:pk>', view_recipe_by_id, name='view_recipe_by_id_view'),
    path('view_foodlists_by_userId/<str:pk>', view_foodlist_by_userId, name='view_foodlists_by_userId_view'),
]