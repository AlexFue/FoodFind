from django.db import models

class User(models.Model):
    userId = models.AutoField(primary_key=True)
    name = models.CharField('Name', max_length=100)
    username = models.CharField('Username', max_length=100)
    password = models.CharField('Password', max_length=100)

class Recipe(models.Model):
    recipeId = models.AutoField(primary_key=True)
    user = models.ForeignKey(User, blank=True, null=True, on_delete=models.CASCADE)
    name = models.CharField('Name', max_length=100)
    description = models.CharField('Description', max_length=500)
    image = models.ImageField(upload_to='images')

class FoodList(models.Model):
    foodListId = models.AutoField(primary_key=True)
    user = models.ForeignKey(User, blank=True, null=True, on_delete=models.CASCADE)
    recipes = models.ManyToManyField(Recipe, blank=True)

class SavedFoods(models.Model):
    savedFoodsId = models.AutoField(primary_key=True)
    user = models.ForeignKey(User, blank=True, null=True, on_delete=models.CASCADE)
    recipes = models.ManyToManyField(Recipe, blank=True)

