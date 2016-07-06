from django.db import models
from django.conf import settings
Area = [
    ("Kathmandu","Kathmandu"),
    ("Biratnagar","Biratnagar"),
    ("Dhulikhel","Dhulikhel"),
    ("Jhapa","Jhapa"),
]
Product=[
    ("Chauchau","Chauchau"),
    ("SomethingElse","SomethingElse"),
    ("SubashLeJptBolyo","SubashLeJPtBolyo"),

]

class Raw_Prices(models.Model):
    product=models.CharField(max_length=3,choices=Product)
    area=models.CharField(max_length=3,choices=Area)
    price=models.IntegerField()
    username=models.ForeignKey(settings.AUTH_USER_MODEL)
    date=models.DateTimeField(auto_now_add=True)

    def __str__(self):
        return str(self.date)+str(self.username)


# tHIS WILL BE UPDATED VIA AN EXTERNAL CORNTAB SCRIPT
class Prices(models.Model):
    product=models.CharField(max_length=3,choices=Product)
    area=models.CharField(max_length=3,choices=Area)
    price=models.IntegerField()

    def __str__(self):
        return  str(self.product)+" "+str(self.area)

