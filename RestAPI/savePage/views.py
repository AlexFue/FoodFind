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
    # user = User.objects.get(userId=request.session['userId']) #apply once combing front/back
    user = User.objects.get(userId=userId) #delete once combing front/back
    savedFoodsList = SavedFoods.objects.get(user=user)
    recipes = savedFoodsList.recipes.all()
    serializer = RecipeSerializer(recipes, many=True)
    return Response(serializer.data)

@api_view(['GET'])
def removeSavedRecipe(request, userId, recipeId):
    user = User.objects.get(userId=userId)
    savedFoodList = SavedFoods.objects.get(user=user)
    recipe = Recipe.objects.get(recipeId=recipeId)
    savedFoodList.recipes.remove(recipe)
    return Response(SavedFoodsSerializer(savedFoodList).data)

