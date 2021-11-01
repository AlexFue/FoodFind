from django.contrib import admin
from .models import User
from .models import Recipe
from .models import SavedFoods
from .models import FoodList

# Register your models here.
admin.site.register(User)
admin.site.register(Recipe)
admin.site.register(SavedFoods)
admin.site.register(FoodList)
