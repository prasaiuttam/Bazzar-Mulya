# -*- coding: utf-8 -*-
# Generated by Django 1.9.4 on 2016-07-31 11:41
from __future__ import unicode_literals

from django.conf import settings
from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
        ('website', '0003_prices'),
    ]

    operations = [
        migrations.CreateModel(
            name='Category',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('category', models.CharField(max_length=30)),
            ],
        ),
        migrations.CreateModel(
            name='Notification',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('notification_type', models.CharField(max_length=150)),
                ('is_read', models.BooleanField()),
                ('created_at', models.DateTimeField()),
                ('actor_id', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='actor_id', to=settings.AUTH_USER_MODEL)),
            ],
        ),
        migrations.AlterField(
            model_name='prices',
            name='area',
            field=models.CharField(max_length=30),
        ),
        migrations.AlterField(
            model_name='prices',
            name='product',
            field=models.CharField(max_length=30),
        ),
        migrations.AlterField(
            model_name='raw_prices',
            name='area',
            field=models.CharField(max_length=30),
        ),
        migrations.AlterField(
            model_name='raw_prices',
            name='product',
            field=models.CharField(max_length=30),
        ),
        migrations.AddField(
            model_name='notification',
            name='product',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='website.Prices'),
        ),
        migrations.AddField(
            model_name='notification',
            name='reciever_id',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='reciever_id', to=settings.AUTH_USER_MODEL),
        ),
        migrations.AddField(
            model_name='category',
            name='product',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='website.Prices'),
        ),
    ]
