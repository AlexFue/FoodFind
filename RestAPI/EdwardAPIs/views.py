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
from ..models import SavedFoods
from .serializer import RecipeSerializer
from .serializer import UserSerializer
from .serializer import LoginSerializer
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

        user_taken = found_user_name(u_username)
        if user_taken:
            data = {'success': 'bad', 'message': 'username already taken'}
            return Response(data, status=status.HTTP_200_OK)

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
            food_list = FoodList.objects.create(user=user_obj)

            # save it
            food_list.save()

            # create saved food list
            save_list = SavedFoods.objects.create(user=user_obj)

            # save it
            save_list.save()

            data = {'success': 'Good', 'message': 'Created account'}
            return Response(serializer2.data, status=status.HTTP_201_CREATED)
        return Response(serializer.data, status=status.HTTP_406_NOT_ACCEPTABLE)


# Login
@api_view(['POST'])
def login_api(request, u_username, u_password):
    if request.method == 'POST':
        # API endpoint: '[url]/api/login/'
        valid_user = found_user_name(u_username)
        valid_password = found_password(u_username, u_password)
        data = {}
        if valid_user and valid_password:
            data['success'] = 'good'
            user = User.objects.get(username=u_username)
            print("UserId for " + u_username + " = ", user.userId)
            request.session['password'] = u_password
            request.session['username'] = u_username
            request.session['userId'] = user.userId
            request.session['name'] = user.name
            return Response(data, status=status.HTTP_200_OK)
        else:
            data['success'] = 'bad'
            print('%%%%%\n', valid_user, valid_password)
            if valid_user and not valid_password:
                data['status'] = 'bad password'
                return Response(data, status=status.HTTP_200_OK)
            elif not valid_user:
                data['status'] = 'bad username'
                return Response(data, status=status.HTTP_200_OK)
    return Response("Not a valid username or password", status=status.HTTP_400_BAD_REQUEST)


# Logout
@api_view(['POST'])
def logout_api(request):
    data = {}
    del request.session['userId']
    del request.session['username']
    del request.session['password']
    del request.session['name']
    data['success'] = 'good'
    data['message'] = 'Log out is successful'
    return Response(data, status=status.HTTP_200_OK)


def found_user_name(u_name):
    users = User.objects.all()
    serializer = UserSerializer(users, many=True)
    user_list = json.loads(json.dumps(serializer.data))
    found_user = False
    for obj in user_list:
        if obj['username'] == u_name:
            found_user = True
            break
    return found_user


def found_password(u_name, u_pass):
    users = User.objects.all()
    serializer = UserSerializer(users, many=True)
    user_list = json.loads(json.dumps(serializer.data))
    found_pass = False
    for obj in user_list:
        if obj['username'] == u_name and obj['password'] == u_pass:
            found_pass = True
            break
    return found_pass
