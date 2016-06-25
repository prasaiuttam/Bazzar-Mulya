"""bazzar URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.9/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from django.conf.urls import url, include
from rest_framework import routers
from rest_framework.authtoken.views import obtain_auth_token
from bazzar.api import views
from django.contrib import admin

router = routers.DefaultRouter()

router.register(r'price', views.PriceViewSet)
router.register(r'rawdata',views.RawPriceViewSet)
router.register(r'users',views.UserViewSet)
print("\n\n",views.PriceSerializer,"asd")
# Wire up our API using automatic URL routing.
# Additionally, we include login URLs for the browsable API.


urlpatterns = [

    url(r'^admin',admin.site.urls),
    url(r'^api/token/',obtain_auth_token,name="api-token"),
    url(r'^api', include(router.urls)),
    url(r'^api-auth/', include('rest_framework.urls', namespace='rest_framework')),
    url(r'^website/',include('website.urls')),
]
