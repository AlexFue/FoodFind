from rest_framework import status
from rest_framework.response import Response
from rest_framework.decorators import api_view
from django.views.decorators.csrf import csrf_exempt
from RestAPI.models import User, Recipe, FoodList
from .serializers import UserSerializer, RecipeSerializer, FoodListSerializer

#Create a food/recipe in the FoodList by userId, foodListId, and recipeId
#[url]/create_food_by_user/<userId>/<foodListId>/<recipeId>
@api_view(['POST'])
def create_food_by_user(request, pk1, pk2, pk3):
    try:
        user = User.objects.get(userId=pk1)
        foodlist = FoodList.objects.get(foodListId=pk2)
        recipe = Recipe.objects.get(recipeId=pk3)
    except:
        return Response("User_ID or FoodList_ID is not found", status=status.HTTP_400_BAD_REQUEST)
    foodlist.recipes.add(Recipe.objects.get(recipeId=pk3))
    return Response(f'Recipe {recipe.name} added to food list, {user.username}.', status=status.HTTP_200_OK)

#Delete a food/recipe in the foodlist by userId, foodListId, and recipeId
#[url]/delete_food_by_user/<userId>/<foodListId>/<recipeId>
@api_view(['DELETE', 'GET'])
def delete_food_by_user(request, pk1, pk2, pk3):
    try:
        user = User.objects.get(userId=pk1)
        foodlist = FoodList.objects.get(foodListId=pk2)
        recipe = Recipe.objects.get(recipeId=pk3)
    except:
        return Response("User_ID or FoodList_ID or Recipe_ID is not found", status=status.HTTP_400_BAD_REQUEST)
    foodlist.recipes.remove(Recipe.objects.get(recipeId=pk3))
    return Response(f'Recipe {recipe.name} deleted.', status=status.HTTP_200_OK)

#Update a food/recipe in the foodlist by userId, foodListId, and recipeId
#[url]/update_food_by_user/<userId>/<foodListId>/<recipeId>
@csrf_exempt #might not need to add this
@api_view(['PUT'])
def update_food_by_user(request, pk1, pk2, pk3):
    try:
        user = User.objects.get(userId=pk1)
        foodlist = FoodList.objects.get(foodListId=pk2)
        recipe = Recipe.objects.get(recipeId=pk3)
    except:
        return Response("User_ID or Food_List ID or Recipe_ID is not found", status=status.HTTP_400_BAD_REQUEST)
    serializer = RecipeSerializer(instance=recipe, data=request.data)
    if serializer.is_valid():
        serializer.save()
        return Response(serializer.data, status=status.HTTP_202_ACCEPTED)
    return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

#View all food/recipes in the foodlist by userId and foodListId
#[url]/view_foods_by_user/<userId>/<foodListId>
@api_view(['GET'])
def view_foods_by_user(request, pk1, pk2):
    try:
        user = User.objects.get(userId=pk1)
        print(user); 
        foodlist = FoodList.objects.get(foodListId=pk2)
    except:
        return Response("User_ID or FoodList_ID is not found", status=status.HTTP_400_BAD_REQUEST)
    serializer = FoodListSerializer(foodlist, many=False)
    return Response(serializer.data, status=status.HTTP_200_OK)

#Allow user to update their profile
#[url]/api/update_user/<id>/
@api_view(['PUT'])
def update_user(request, pk):
    try: 
        user = User.objects.get(userId=pk)
    except User.DoesNotExist:
        return Response("User_ID is not found", status=status.HTTP_400_BAD_REQUEST)
    serializer = UserSerializer(instance = user, data=request.data)
    if serializer.is_valid():
        serializer.save()
        return Response(serializer.data, status=status.HTTP_202_ACCEPTED)
    return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

#view all items in Item
#[url]/view_items/
@api_view(['GET'])
def view_items(request):
    foodlist = Recipe.objects.all()
    serializer = RecipeSerializer(foodlist, many=True)
    return Response(serializer.data, status=status.HTTP_200_OK)