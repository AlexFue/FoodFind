from rest_framework import serializers
from ..models import User
from ..models import Recipe
from ..models import FoodList
from ..models import SavedFoods

class FoodListSerializer(serializers.ModelSerializer):
    class Meta:
        model = FoodList
        fields = ['user', 'recipes']

class SavedFoodsSerializer(serializers.ModelSerializer):
    class Meta:
        model = SavedFoods
        fields = ['user', 'recipes']