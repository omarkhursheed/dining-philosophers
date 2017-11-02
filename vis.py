import matplotlib.pyplot as plt 
import time
import math
fig1 = plt.figure()
ax1 = fig1.add_subplot(111, aspect='equal')
ax1.set_xlim([0,50])
ax1.set_ylim([0,50])

lines = []
content = []
with open("Spivak.txt") as f0:
    content0 = f0.readlines()
# you may also want to remove whitespace characters like `\n` at the end of each line

content0 = [x.strip() for x in content0] 
content.append(content0)
with open("Derrida.txt") as f1:
    content1 = f1.readlines()
# you may also want to remove whitespace characters like `\n` at the end of each line

content1 = [x.strip() for x in content1] 
content.append(content1)
with open("Zizek.txt") as f2:
    content2 = f2.readlines()
# you may also want to remove whitespace characters like `\n` at the end of each line

content2 = [x.strip() for x in content2] 
content.append(content2)
with open("Hume.txt") as f3:
    content3 = f3.readlines()
# you may also want to remove whitespace characters like `\n` at the end of each line

content3 = [x.strip() for x in content3] 
content.append(content3)
with open("Neitzche.txt") as f4:
    content4 = f4.readlines()
# you may also want to remove whitespace characters like `\n` at the end of each line

content4 = [x.strip() for x in content4] 
content.append(content4)

print(content)
timer = 0
temptext = ["","","","",""]


def draw(a, b, c, d, e):

	plt.cla()

	ax1.set_xlim([0,50])
	ax1.set_ylim([0,50])	
	circle = plt.Circle((25, 25), 5, fc='blue', ec='black')
	plt.gca().add_patch(circle)

	x0 = [25,25]
	y0 = [33,38]
	pi = 3.14
	line0 = plt.plot(x0,y0,color='green')
	lines.append(line0)
	ax1.text(25,38,"0")
	ax1.text(29,32,"Spivak")
	ax1.text(29,30,a)
	ax1.figure.canvas.draw()

	x1 = [33, 38]
	y1 = [25, 25]
	line1 = plt.plot(x1,y1,color='green')
	lines.append(line1)
	ax1.text(38,25,"1")
	ax1.text(34,21,"Derrida")
	ax1.text(34,19,b)
	ax1.figure.canvas.draw()


	x4 = [29,31]
	y4 = [18,13]
	line4 = plt.plot(x4,y4,color ='green')
	lines.append(line4)
	ax1.text(31,14,"2")
	ax1.text(14,33,"Neitzche")
	ax1.text(14,31,c)
	ax1.figure.canvas.draw()

	x3 = [21,17]
	y3 = [18,13]
	line3 = plt.plot(x3,y3,color='green')
	lines.append(line3)
	ax1.text(16,12,"3")
	ax1.text(11,20,"Hume")
	ax1.text(11,18,d)
	ax1.figure.canvas.draw()


	x2 = [12, 17]
	y2 = [25, 25]
	line2 = plt.plot(x2,y2,color='green')
	lines.append(line2)
	ax1.text(12,25,"4")
	ax1.text(22,15,"Zizek")
	ax1.text(22,13,e)
	ax1.figure.canvas.draw()
	plt.pause(1)
	plt.ion()
print ("sedwdf")
print(content[1][0].split(',')[0])
iterators = [0,0,0,0,0]
while(True):
	flag = [1,1,1,1,1]
	plt.gca()
	print(len(content0))
	if(iterators[0] == len(content[0])):
		flag[0] = 0
		temptext[0] = " has left" 
	elif(int(content[0][iterators[0]].split(',')[1]) == timer):
		while(int(content[0][iterators[0]].split(',')[1])==timer):
			iterators[0]+=1
		iterators[0]-=1
		temptext[0] = content[0][iterators[0]].split(',')[0] 
		iterators[0]+=1
		print("icrement"+str(iterators[0]))
	
	if(iterators[1] == len(content[1])):
		flag[1] = 0
		temptext[1] = " has left" 
	elif(int(content[1][iterators[1]].split(',')[1]) == timer):
		while(int(content[1][iterators[1]].split(',')[1]) == timer):
			iterators[1]+=1
		iterators[1]-=1
		temptext[1] = content[1][iterators[1]].split(',')[0]
		iterators[1]+=1

	if(iterators[2] == len(content[2])):
		flag[2] = 0
		temptext[2] = " has left" 
	elif(int(content[2][iterators[2]].split(',')[1]) == timer):
		while(int(content[2][iterators[2]].split(',')[1])==timer):
			iterators[2]+=1
		iterators[2]-=1
		temptext[2] = content[2][iterators[2]].split(',')[0] 
		iterators[2]+=1
	if(iterators[3] == len(content[3])):
		flag[3] = 0
		temptext[3] = " has left" 
	elif(int(content[3][iterators[3]].split(',')[1]) == timer):
		while(int(content[3][iterators[3]].split(',')[1])==timer):
			iterators[3]+=1
		iterators[3]-=1
		temptext[3] = content[3][iterators[3]].split(',')[0] 
		iterators[3]+=1
	if(iterators[4] == len(content[4])):
		flag[4] = 0
		temptext[4] = " has left" 
	elif(int(content[4][iterators[4]].split(',')[1]) == timer):
		while(int(content[4][iterators[4]].split(',')[1])==timer):
			iterators[4]+=1
		iterators[4]-=1		
		temptext[4] = content[4][iterators[4]].split(',')[0]
		iterators[4]+=1
	print("Iterators")
	print(iterators)
	draw(temptext[0],temptext[1],temptext[2],temptext[3],temptext[4])
	print("Soemtef")
	if(flag[0] == 0 and flag[1] == 0 and flag[2] == 0 and flag[3] == 0 and flag[4] == 0):
		break
	time.sleep(1)
	timer+=1


	