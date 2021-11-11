# libraries for django and REST APIs
from rest_framework import status
from rest_framework.response import Response
from rest_framework.decorators import api_view
import json
from django.shortcuts import render, redirect
from django.contrib.auth import authenticate, login
import requests

# files for models, serializer, and views
from ..models import Recipe
from .serializer import RecipeSerializer
from .serializer import UserSerializer
from .. import views


# Get all of the foods that every user has created
@api_view(['GET'])
def foods_users_api(request):
    # API endpoint: '[url]/api/foods-users-api/'
    if request.method == 'GET':
        print('GET for foods_users_api')
        print('-----------------------')
        foods = Recipe.objects.all()
        serializer = RecipeSerializer(foods, many=True)
        json_obj = json.loads(json.dumps(serializer.data))
        print('JSON object:\n', json_obj)
        return Response(serializer.data)


# Create a user/account
@api_view(['POST'])
def create_user_api(request, u_name, u_username, u_password):
    if request.method == 'POST':
        # API endpoint: '[url]/api/create-user-api/'
        print('POST for create_user_api')
        print('------------------------')
        user_data = {
            'name': u_name,
            'username': u_username,
            'password': u_password
        }
        serializer = UserSerializer(data=user_data)
        if serializer.is_valid():
            serializer.save()
            print(json.loads(json.dumps(serializer.data)))
            return Response(status=status.HTTP_201_CREATED)
        return Response(serializer.data, status=status.HTTP_406_NOT_ACCEPTABLE)
