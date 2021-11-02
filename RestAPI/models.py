from django.db import models

class User(models.Model):
    userId = models.AutoField(primary_key=True)
    name = models.CharField(default=None, max_length=100)
    username = models.CharField(default=None, max_length=100)
    password = models.CharField(default=None, max_length=100)

class Recipe(models.Model):
    recipeId = models.AutoField(primary_key=True)
    user = models.ForeignKey(User, blank=True, null=True, on_delete=models.CASCADE, default=None)
    name = models.CharField(default='New Recipe', max_length=100)
    description = models.CharField(default='Description', max_length=500)
    image = models.URLField(default='')

class FoodList(models.Model):
    foodListId = models.AutoField(primary_key=True)
    user = models.ForeignKey(User, blank=True, null=True, on_delete=models.CASCADE, default=None)
    recipes = models.ManyToManyField(Recipe, blank=True)

class SavedFoods(models.Model):
    savedFoodsId = models.AutoField(primary_key=True)
    user = models.ForeignKey(User, blank=True, null=True, on_delete=models.CASCADE, default=None)
    recipes = models.ManyToManyField(Recipe, blank=True)

