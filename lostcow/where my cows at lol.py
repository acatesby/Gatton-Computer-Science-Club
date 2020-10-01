x = int(input("Where is John starting out?"))
cowPos = int(input("Where is the cow?"))


cowPos -= x
x = 0
farmerPos = 0
distanceTraveled = 0
newPos = 1
turn = 1

while (farmerPos - cowPos) * (cowPos / abs(cowPos)) < 0:
	distanceTraveled += abs(newPos) + abs(farmerPos)
	farmerPos = newPos
	newPos = (-2)**turn
	turn += 1

distanceTraveled -= (farmerPos - cowPos) * (cowPos / abs(cowPos))
print(int(distanceTraveled))