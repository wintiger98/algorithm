# 순차 탐색
def sequential_search(n, target, array: list):
    for i in range(n):
        if array[i] == target:
            return i + 1
            
print("생성할 원소 개수를 입력한 후 한 칸을 띄고 찾을 문자열을 입력하세요")
input_data = input().split()
n = int(input_data[0])
target = input_data[1]

print("앞서 적은 개수만큼 문자열을 한 칸씩 띄워서 입력하세요.")
array = input().split()

print(sequential_search(n, target, array))