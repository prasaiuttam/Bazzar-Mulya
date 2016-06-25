from rest_framework import serializers
from website.models import Prices,Raw_Prices
from django.contrib.auth.models import User
class PriceSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Prices
        fields = ('product','area','price')


class RawPriceSerializer(serializers.Serializer):
    product_display=serializers.SerializerMethodField('get_product')
    user=serializers.SerializerMethodField('get_user')
    class Meta:
        model= Raw_Prices
        fields=('product_display','area','price')

    def get_product(self):
        pass


    def get_user(self):
        pass
