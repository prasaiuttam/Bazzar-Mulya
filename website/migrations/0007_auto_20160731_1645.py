# -*- coding: utf-8 -*-
# Generated by Django 1.9.4 on 2016-07-31 16:45
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('website', '0006_auto_20160731_1608'),
    ]

    operations = [
        migrations.AlterField(
            model_name='newsfeed',
            name='date',
            field=models.DateField(auto_now_add=True),
        ),
    ]