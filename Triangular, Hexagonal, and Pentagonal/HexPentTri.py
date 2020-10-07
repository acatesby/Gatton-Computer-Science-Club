import math

c = 2
isTrue = True

while True:
	numH = c * (2 * c - 1)
	if (((24 * numH + 1) ** 0.5) + 1) % 6 == 0:
		isTrue = False
		print(numH)
		break
	else:
		c += 1
		