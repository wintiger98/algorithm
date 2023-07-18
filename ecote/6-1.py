# 선택정렬
array = [1,3,2,4,5,8,7,6,9,0]
print("처음: ", array)
n = len(array)
for i in range(n):
    min_index = i
    for j in range(i+1, n):
        if array[min_index] > array[j]:
            min_index = j
    array[i], array[min_index] = array[min_index], array[i]

print("정렬: ", array)