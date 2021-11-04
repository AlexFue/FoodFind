from rest_framework import serializers
from ..models import User
from ..models import Recipe
from ..models import FoodList
from ..models import SavedFoods

class RecipeSerializer(serializers.ModelSerializer):
    class Meta:
        model = Recipe
        fields = ['user', 'name', 'description', 'image']

class SavedFoodsSerializer(serializers.ModelSerializer):
    class Meta:
        model = SavedFoods
        fields = ['user', 'recipes']