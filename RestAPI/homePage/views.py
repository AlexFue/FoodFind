from rest_framework import status
from rest_framework.response import Response
from rest_framework.decorators import api_view
import json
from django.shortcuts import render, redirect

from ..models import User
from ..models import SavedFoods
from ..models import Recipe
from .serializers import SavedFoodsSerializer

@api_view(['GET'])
def addFoodToSavedList(request, userId, recipeId):
    try:
        # user = User.objects.get(userId=request.session['userId']) #apply once combing front/back
        user = User.objects.get(userId=userId) #delete once combing front/back
        savedFoodsList = SavedFoods.objects.get(user=user)
        recipe = Recipe.objects.get(recipeId=recipeId)
    except:
        return Response("User_ID or Recipe_ID is not found", status=status.HTTP_400_BAD_REQUEST)
    savedFoodsList.recipes.add(recipe)
    # return Response(SavedFoodsSerializer(savedFoodsList).data)
    return Response(f'Recipe {recipe.name} added to food list, {user.username}.', status=status.HTTP_200_OK)


