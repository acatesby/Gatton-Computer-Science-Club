N = int(input('Enter a number: '))
num_list = [1, 2, 4, 7, 8, 11, 13, 14]
num = N%8-1
if num != -1:
    answer = num_list[num] + (15 * (N // 8))
else:
    answer = num_list[num] + (15 * (N // 8 - 1))
print(answer)
