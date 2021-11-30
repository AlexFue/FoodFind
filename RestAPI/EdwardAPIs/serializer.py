from rest_framework import serializers
from ..models import Recipe
from ..models import User


class RecipeSerializer(serializers.ModelSerializer):
    class Meta:
        model = Recipe
        fields = '__all__'


class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = '__all__'


class LoginSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = '__all__'

    def check(self):
        if self.username_exists(self.validated_data['username']):
            user = User.objects.get(username=self.validated_data['username'])
            if user.password != self.validated_data['password']:
                raise serializers.ValidationError({'failure': 'incorrect password'})
                return False
        else:
            raise serializers.ValidationError({'failure': 'username does not exist'})
            return False

        return True

    def username_exists(self, username):
        if User.objects.filter(username=username).exists():
            return True

        return False