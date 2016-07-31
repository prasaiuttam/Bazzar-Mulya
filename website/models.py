from django.conf import settings
from django.db import models

# Create your models here.

class Raw_Prices(models.Model):
    product=models.CharField(max_length=30)
    area=models.CharField(max_length=30)
    price=models.IntegerField()
    username=models.ForeignKey(settings.AUTH_USER_MODEL)
    date=models.DateTimeField(auto_now_add=True)

    def __str__(self):
        return str(self.date)+str(self.username)


# tHIS WILL BE UPDATED VIA AN EXTERNAL CORNTAB SCRIPT
class Prices(models.Model):
    product=models.CharField(max_length=30)
    area=models.CharField(max_length=30)
    price=models.IntegerField()

    def __str__(self):
        return  str(self.product)

class Category(models.Model):
    category = models.CharField(max_length=30)
    product = models.ForeignKey(Prices)

    def __str__(self):
        return str(self.category)

class Notification(models.Model):
    actor_id = models.ForeignKey(settings.AUTH_USER_MODEL,related_name='actor_id')
    reciever_id = models.ForeignKey(settings.AUTH_USER_MODEL,related_name='reciever_id')
    notification_type = models.CharField(max_length=150)
    product = models.ForeignKey(Prices)
    is_read = models.BooleanField()
    created_at = models.DateTimeField()



class NewsFeed(models.Model):
    date=models.DateTimeField(auto_now_add=True)
    author=models.ForeignKey(settings.AUTH_USER_MODEL)
    title=models.CharField(max_length=100)
    data=models.TextField()
