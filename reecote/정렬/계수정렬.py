array = [7, 5, 9, 0, 3, 1, 5, 2, 9, 1, 2, 5, 3, 4, 2]

count = [0] * (max(array) + 1)

for i in range(len(array)):
    count[array[i]] += 1

for i in range(len(count)):
    for j in range(count[i]):
        print(i, end=" ")
