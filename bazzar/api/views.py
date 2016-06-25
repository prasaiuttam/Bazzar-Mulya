from django.contrib.auth.models import User, Group
from rest_framework import viewsets,permissions,authentication
from bazzar.api.serializers import PriceSerializer,RawPriceSerializer
from website.models import Prices,Raw_Prices



class PriceViewSet(viewsets.ModelViewSet):
    """
    API endpoint that allows users to be viewed or edited.
    """
    queryset = Prices.objects.all()
    serializer_class = PriceSerializer
    permissions_classes=(
        permissions.IsAuthenticatedOrReadOnly
    )

class RawPriceViewSet(viewsets.ModelViewSet):

    permission_classes = (
        permissions.IsAuthenticated,
    )
    queryset = Raw_Prices.objects.all()
    serializer_class = RawPriceSerializer


class UserViewSet(viewsets.ModelViewSet):
    queryset  =  User.objects.all()

