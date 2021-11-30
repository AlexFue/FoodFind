from rest_framework import status
from rest_framework.response import Response
from rest_framework.decorators import api_view
import json
from django.shortcuts import render, redirect

from .serializers import RecipeSerializer
from .serializers import SavedFoodsSerializer
from ..models import User
from ..models import SavedFoods
from ..models import Recipe

@api_view(['GET'])
def getSavedRecipesOfUser(request, userId):
    try:
        # user = User.objects.get(userId=request.session['userId']) #apply once combing front/back
        user = User.objects.get(userId=userId) #delete once combing front/back
        savedFoodsList = SavedFoods.objects.get(user=user)
        recipes = savedFoodsList.recipes.all()
    except:
        return Response("User_ID is not found", status=status.HTTP_400_BAD_REQUEST)
    return Response(RecipeSerializer(recipes, many=True).data, status=status.HTTP_200_OK)

@api_view(['GET'])
def removeSavedRecipe(request, userId, recipeId):
    try:
        # user = User.objects.get(userId=request.session['userId']) #apply once combing front/back
        user = User.objects.get(userId=userId) #delete once combing front/back
        savedFoodList = SavedFoods.objects.get(user=user)
        recipe = Recipe.objects.get(recipeId=recipeId)
    except:
        return Response("User_ID or Recipe_ID is not found", status=status.HTTP_400_BAD_REQUEST)
    savedFoodList.recipes.remove(recipe)
    # return Response(SavedFoodsSerializer(savedFoodList).data)
    return Response(f'Recipe {recipe.name} removed from food list, {user.username}.', status=status.HTTP_200_OK)

