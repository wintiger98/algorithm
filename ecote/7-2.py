# 이진 탐색
def binary_search(target, start, end, array: list):
    if start > end:
        return None
    mid = (start + end) // 2
    if array[mid] == target:
        return mid
    elif array[mid] > target:
        return binary_search(target, start, end-1, array)
    else:
        return binary_search(target, mid+1, end, array)

            
n, target = map(int, input().split())

array = list(map(int, input().split()))

print(binary_search(target, 0, n, array))