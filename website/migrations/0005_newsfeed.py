# -*- coding: utf-8 -*-
# Generated by Django 1.9.4 on 2016-07-31 16:05
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('website', '0004_auto_20160731_1141'),
    ]

    operations = [
        migrations.CreateModel(
            name='NewsFeed',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('date', models.DateTimeField(auto_now_add=True)),
                ('author', models.TextField(max_length=100)),
                ('data', models.TextField()),
            ],
        ),
    ]
