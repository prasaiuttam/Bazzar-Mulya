from django.conf.urls import url
from . import views

urlpatterns=[
	url(r'^signup',views.signUp),
	url(r'^signip',views.signIn),
	url(r'^dataentry',views.dataEntry),
	url(r'^select_data',views.DailyPrice),
]