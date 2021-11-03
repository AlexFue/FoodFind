from rest_framework import status
from rest_framework.response import Response
from rest_framework.decorators import api_view
import json
from django.shortcuts import render, redirect
from .serializers import FoodListSerializer
from .serializers import SavedFoodsSerializer
from ..models import User
from ..models import FoodList
from ..models import SavedFoods

# Function that creates a foodlist for the username that is passed in
def createFoodList(username):
    user = User.objects.get(username=username)
    foodList = FoodList(user=user)
    serializer = FoodListSerializer(foodList)
    if serializer.is_valid():
        serializer.save()
        return True
    return False


# Function that creates a saved foodlist for the username that is passed in
def savedFoodList(username):
    user = User.objects.get(username=username)
    savedFoods = SavedFoods(user=user)
    serializer = SavedFoodsSerializer(savedFoods)
    if serializer.is_valid():
        serializer.save()
        return True
    return False
