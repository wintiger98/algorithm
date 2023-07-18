# 삽입정렬
array = [1,3,2,4,5,8,7,6,9,0]
print("처음: ", array)
n = len(array)
for i in range(1, n):
    for j in range(i, 0, -1):
        if array[j] < array[j-1]:
            array[j], array[j-1] = array[j-1], array[j]
        else:
            break

print("정렬: ", array)