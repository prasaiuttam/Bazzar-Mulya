import datetime

from django.contrib.auth import login,logout,authenticate
from django.contrib.auth.models import Group
from django.shortcuts import render, redirect,HttpResponse
from django.shortcuts import render_to_response
from django.utils import timezone
from django.contrib.auth.models import  User
from .forms import UserForm,UploadForm,SigninForm
from .models import Notification,Raw_Prices,Prices,Category,NewsFeed



def categories(request):
    cat=[i[1] for i in Category ]
    category=dict()
    list=[]
    for i in Category:

        list+=i[1]

    return  render(request,'bazzarmulya/categories.html',{category:list,'category': cat})

def CategoryView(request):
    cat=[i[1] for i in Category ]
    dcat=dict()
    for i in Category:
        dcat[i[1]]=i[0]
    name=request.GET["cat"]
    list = Prices.objects.filter(category=dcat[name])
    print(list)
    return  render(request,"bazarmulya/category_sample.html",{'category': cat,'list':list,'name':name})

def home(request):
    news = NewsFeed.objects.all()
    news=news[::-1]
    news=news[0:3]
    cat=[i[1] for i in Category ]
    print ("***********",cat,"*****************")
    if request.user.is_authenticated():
        try:
            notifications = Notification.objects.filter(reciever_id=request.user,is_read=False)

            for i in notifications:
                i.is_read = True
                i.save()

        except Notification.DoesNotExist:
            notifications = None
        lists = Prices.objects.all()
        print("the lis ist",lists)


        return render(request,'bazarmulya/default_home.html',{
            'notifications' : notifications,
            'lists':lists,
            'category': cat,
            'news':news
    })
    else:
        lists = Prices.objects.all()
        return render(request,'bazarmulya/default_home.html',{'lists':lists,'category':cat})


def NewsFeeds(request):
    a=request.GET["id"];
    news=NewsFeed.objects.get(id=a)
    return  render(request,"bazarmulya/news.html",{'article':news})
    pass

def Login(request):
    cat=[i[1] for i in Category ]
    if request.method == "POST":
        username = request.POST['username']
        password = request.POST['password']
        user = authenticate(username=username,password=password)
        if user is not None:
            if user.is_active:
                login(request,user)
                lists = Prices.objects.all()
                return render(request,'bazarmulya/default_home.html',{
                    'message':"You're Logged In as",
                    'lists':lists
                })
        else:
            return render(request,'bazarmulya/login.html',{'message':"Enter a Valid Username and Password",'category': cat})

    return render(request,'bazarmulya/login.html',{})



def Register(request):
    cat=[i[1] for i in Category ]
    form = SigninForm(request.POST or None)
    if form.is_valid():
        email=form.cleaned_data.get('email')
        username=form.cleaned_data.get('username')
        password = form.cleaned_data.get('password')
        #if password1 == password2:
         #   password = password1
        #else:
         #   return render(request,'bazarmulya/register.html',{'message':"Password Did not Match"})

        user=User.objects.create_user(username,email,password)
        user.save()
        return render(request, 'bazarmulya/login.html', {'message': "Congrats!!! You have been Registered. Now Sign In"})

    return render(request,'bazarmulya/register.html',{'form':form,'category': cat})


def Logout_view(request):
    cat=[i[1] for i in Category ]
    logout(request)
    message = "You are Logged Out"
    lists = Prices.objects.all()
    return render(request,'bazarmulya/default_home.html',{'message':message,'lists':lists,'category': cat})

def Search(request):
    cat=[i[1] for i in Category ]
    if request.method == "POST":
        print ("Subash le Bitch bhanyo")
        search_text = request.POST['search_text']
        if search_text is not None and search_text != u"":
            search_text = request.POST['search_text']
            products = Prices.objects.filter(product__contains=search_text)
            for i in products:
                print(i)
        else:
            products = []
        return render_to_response( 'bazarmulya/search.html', {'products' : products,'category': cat})


def Search_detail(request,product_id):
    cat=[i[1] for i in Category ]
    objects = Prices.objects.filter(id=product_id)
    objects=Prices.objects.filter(product__contains=objects[0].product)
    return render(request,'bazarmulya/Search_detail.html',{'objects':objects,'category': cat})


def NotifyLike(request,getid):
    cat=[i[1] for i in Category ]
    group = Group.objects.get(name='LocalAdmins')
    reciever_users = group.user_set.all()
    try:
        price = Prices.objects.get(id = getid)
    except Prices.DoesNotExist:
        price =None

    for i in reciever_users:
        notification = Notification(actor_id=request.user,reciever_id=i,notification_type='Like',
                                    product=price,is_read=False,created_at=timezone.now())
        notification.save()

    return redirect('/website')

def UpdateValues(request):
    cat=[i[1] for i in Category ]
    if request.method == 'POST':
        form = UploadForm(request.POST or None)
        if form.is_valid():
            product_entry = form.cleaned_data.get("product")
            area_entry = form.cleaned_data.get("area")
            price_entry = int(form.cleaned_data.get("price"))
            username_entry = request.user
            if username_entry.is_authenticated():
                entry = Raw_Prices(product=product_entry, area=area_entry, price=price_entry, username=username_entry)
                entry.save()
                return HttpResponse("Thanks for Uploading values")
    else:
        form = UploadForm()
    upload_values = Raw_Prices.objects.all()
    return render(request, "bazarmulya/upload.html", {'form':form,'upload_values':upload_values,'category': cat})
