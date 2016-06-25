from django.shortcuts import render
from .forms import SignInForm,DataEntryForm,PriceData
from django.contrib.auth.models import User
from .models import  Raw_Prices,Prices
from django.contrib.auth.models import Group
from django.shortcuts import redirect

# Create your views here.
def signUp(requests):
	form = SignInForm(requests.POST or None)
	if form.is_valid():
		email=form.cleaned_data.get("email")
		user=form.cleaned_data.get("username")
		password=form.cleaned_data.get("password1")
		print(email,user,password)
		#TODO SAVE IN A JUNK DATABASE
		#GENERATE A KEY
		#REDICRECT TO VERIFY
		#CREATE USER
		user=User.objects.create_user(user,email,password)
		user.save()
		return redirect('/website/signin')
	else:
		context={
			"title":"Hello"+" "+ str(requests.user),
			"form":form
		}
		return render(requests,"SignUnForm.html",context)

def signIn(requests):
	pass
	#return render(requests,"SignInForm",context)


def dataEntry(requests):
	form=DataEntryForm(requests.POST or None)
	if form.is_valid():
		product_entry=form.cleaned_data.get("product")
		area_entry=form.cleaned_data.get("area")
		price_entry=int(form.cleaned_data.get("price"))
		username_entry=requests.user
		if username_entry.is_authenticated():
			entry=Raw_Prices(product=product_entry,area=area_entry,price=price_entry,username=username_entry)
			entry.save()
			print("bhayo")
			return render(requests,"SignUnForm.html",{"title":"Request Saved As %s"%(requests.user)})
		else:
			return render(requests,"SignUnForm.html",{"title":"You Need To Be Signed In"})
	context={
		"title":"Please Send Data",
		"form":form
	}
	return render(requests,"SignUnForm.html",context)


def DailyPrice(requests):
		model=Prices.objects.all()
		modelstring=""
		for i in model:
			modelstring+=i.area
			modelstring+=str(i.price)
			modelstring+='\n'
		form=PriceData(requests.POST
					   or None)
		grp=Group.objects.get_or_create(name='Local_Admins')[0]
		if form.is_valid():
			product_entry = form.cleaned_data.get("product")
			area_entry = form.cleaned_data.get("area")
			price_entry = int(form.cleaned_data.get("price"))
			username_entry = requests.user
			if username_entry.is_authenticated():
				if grp in requests.user.groups.all():
					entry = Prices(product=product_entry, area=area_entry, price=price_entry)
					entry.save()
					print("bhayo")
					return render(requests, "SignUnForm.html", {"title": "Request Saved As %s" % (requests.user)})
				else:
					return render(requests, "SignUnForm.html", {"title": "You dont have permission"})

		context = {
			"title": "Please Select Data",
			"form": form,
			"model":modelstring,
		}
		return render(requests, "SignUnForm.html", context)




