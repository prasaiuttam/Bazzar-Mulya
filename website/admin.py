from django.contrib import admin
from .models import Raw_Prices,Prices,Notification,Category,NewsFeed
admin.site.register(Raw_Prices)
admin.site.register(Prices)
# Register your models here.
admin.site.register(Notification)
admin.site.register(Category)
admin.site.register(NewsFeed)
