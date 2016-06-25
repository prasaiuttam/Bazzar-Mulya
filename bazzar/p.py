print(x."		",y,"		","del(x)","		","del2(x)")

def diff(l):
	b=[]
	for i in len(l)-1:
		b.append(l[i+1]-l[i])
	return b


l=[14.035,13.674,13.257,12.734,12.089,11.309]
b=diff(l)
c=diff(b)
d=diff(c)


