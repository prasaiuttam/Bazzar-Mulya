from django import forms
from django.contrib.auth.forms import UserCreationForm
from django.contrib.auth.models import User
from .models import Raw_Prices,Prices

class SignInForm(UserCreationForm):
	#email = forms.EmailField(required=True)
	class Meta:
		model=User
		fields=["username","email","password1","password2"]



class DataEntryForm(forms.ModelForm):

	class Meta:
		model=Raw_Prices
		fields=["product","area","price"]

class PriceData(forms.ModelForm):

	class Meta:
		model=Prices
		fields=['product','area','price']
