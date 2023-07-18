# 계수정렬
array = [1,3,2,4,5,8,7,6,9,0]
print("처음: ", array)
n = len(array)
# 모든 범위를 포함하는 리스트 생성
count = [0] * (max(array)+1)
for i in array:
    count[i] += 1

for i in range(len(count)):
    for j in range(count[i]):
        print(i, end=" ")

# print("정렬: ", array)