# libraries for django and REST APIs
from rest_framework import status
from rest_framework.response import Response
from rest_framework.decorators import api_view
import json
from django.shortcuts import render, redirect
from django.contrib.auth import authenticate, login, get_user_model, update_session_auth_hash
import requests

# files for models, serializer, and views
from ..models import Recipe
from ..models import User
from ..models import FoodList
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
            # Save the new user
            serializer.save()
            print(json.loads(json.dumps(serializer.data)))

            # Get the user that was just created
            user_obj = User.objects.get(username=u_username)
            serializer2 = UserSerializer(user_obj, many=False)
            new_user_json = json.loads(json.dumps(serializer2.data))
            print("Getting new user created:\n", new_user_json)

            # create food list
            # issues creating the food list
            print("Type: ", type(user_obj))
            food_list = FoodList.objects.create(new_user_json['userId'])

            # save it
            food_list.save()
            return Response(status=status.HTTP_201_CREATED)
        return Response(serializer.data, status=status.HTTP_406_NOT_ACCEPTABLE)


# Login
@api_view(['POST'])
def login_api(request, u_username, u_password):
    if request.method == 'POST':
        # API endpoint: '[url]/api/login/'
        username = u_username
        password = u_password
        authenticated = authenticate(username=username, password=password)
        if authenticated is not None:
            user = User.objects.get(username=username)
            serializer = UserSerializer(user, many=False)
            return Response(serializer.data, status=status.HTTP_200_OK)
        return Response("Not a valid username or password", status=status.HTTP_400_BAD_REQUEST)
