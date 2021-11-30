from rest_framework import serializers
from RestAPI.models import User, Recipe, FoodList


class UserSerializer(serializers.ModelSerializer):    
    class Meta:
        model = User
        fields = ['userId', 'name', 'username', 'password']

class RecipeSerializer(serializers.ModelSerializer):
    user = UserSerializer(many=False)
    class Meta: 
        model = Recipe
        fields = ['recipeId', 'user', 'name', 'description', 'image']

class FoodListSerializer(serializers.ModelSerializer):
    recipes = RecipeSerializer(many=True)
    class Meta: 
        model = FoodList
        fields = ['foodListId', 'user', 'recipes']