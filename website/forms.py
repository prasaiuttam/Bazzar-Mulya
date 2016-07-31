from django import forms
from django.contrib.auth.models import User
from .models import Raw_Prices
from django.contrib.auth.forms import UserCreationForm

class UserForm(forms.ModelForm):
    password1 = forms.CharField(widget=forms.PasswordInput,label=("Password"))
    password2 = forms.CharField(label=("Password confirmation"),
                                widget=forms.PasswordInput,
                                help_text=("Enter the same password as above, for verification."))

    class Meta:
        model = User
        fields = ['username','email','password1','password2']


class UploadForm(forms.ModelForm):
    class Meta:
        model = Raw_Prices
        fields = ['product','area','price']


class SigninForm(UserCreationForm):
    class Meta:
        model=User
        fields=['username','email','password1','password2']