from django.conf.urls import url

from . import views

urlpatterns = [

    url(r'^$', views.home, name='home'),
    url(r'^login/$', views.Login, name='login'),
    url(r'^register/$', views.Register, name='register'),
    url(r'^logout/$', views.Logout_view, name='logout'),
    url(r'^search/$', views.Search, name='search'),
    url(r'^search/(?P<product_id>[0-9]+)/$',views.Search_detail),
    url(r'^notifylike/(?P<getid>[0-9]+)$',views.NotifyLike),
    url(r'^updatevalues/$',views.UpdateValues)


]
