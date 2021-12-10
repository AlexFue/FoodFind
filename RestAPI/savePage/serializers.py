from rest_framework import serializers
from ..models import User
from ..models import Recipe
from ..models import FoodList
from ..models import SavedFoods
from ..models import User

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ['userId', 'name', 'username', 'password']

class RecipeSerializer(serializers.ModelSerializer):
    user = UserSerializer(many=False)
    class Meta:
        model = Recipe
        fields = ['recipeId', 'user', 'name', 'description', 'image']

class SavedFoodsSerializer(serializers.ModelSerializer):
    recipes = RecipeSerializer(many=True)
    # user = UserSerializer(many=False)
    class Meta:
        model = SavedFoods
        fields = ['savedFoodsId', 'user', 'recipes']