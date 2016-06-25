#Written By Prashant
#Script Created to Create Groups and Permissions
#Please Run This Script Using manage.py shell and not 
#Regular Python3 Interpreter

from django.contrib.auth.models import User,Group,Permission


regularusers=Group.objects.get_or_create(name='Regular_Users')
localadmins=Group.objects.get_or_create(name='Local_Admins')
mainadmins=Group.objects.get_or_create(name='MainAdmins')



###Adding Myself to All Groups
user=User.objects.get(username="prashant")
user.groups.add(regularusers[0],localadmins[0],mainadmins[0])
