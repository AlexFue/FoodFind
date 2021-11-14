from rest_framework import serializers
from ..models import SavedFoods

class SavedFoodsSerializer(serializers.ModelSerializer):
    class Meta:
        model = SavedFoods
        fields = ['user', 'recipes']