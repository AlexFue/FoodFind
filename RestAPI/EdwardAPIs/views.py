# libraries for django and REST APIs
from rest_framework import status
from rest_framework.response import Response
from rest_framework.decorators import api_view
import json
from django.shortcuts import render, redirect
from django.contrib.auth import authenticate, login
import requests

# files for models, serializer, and views
# from ..models import / /
from .serializer import RecipeSerializer

from .. import views


# Get all of the foods that every user has created
@api_view(['GET'])
def foods_users_api(request):
    # API endpoint: '[url]/apiV1/food-users-api/'
    if request.method == 'GET':
        print("GET for foods_users_api")
        print("-----------------------")
        # foods = Recipe.objects.all()
        # serializer = RecipeSerializer(foods, many=True)
        # json_obj = json.loads(json.dumps(serializer.data))
        # print("JSON object:\n",json_obj)
        # return Response(serializer.data)
