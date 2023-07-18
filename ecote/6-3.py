# 퀵정렬
array = [1,3,2,4,5,8,7,6,9,0]
print("처음: ", array)
n = len(array)

def quick_sort(array, start, end):
    if start >= end:
        return
    pivot = start
    left = start + 1
    right = end

    while left <= right:
        # 피벗보다 큰 데이터를 찾을 때까지 반복
        while left <= end and array[left] <= array[pivot]:
            left += 1
        # 피벗보다 작은 데이터를 찾을 때까지 반복
        while right > start and array[right] >= array[pivot]:
            right -= 1
        # 엇갈렸다면 작은 값과 피벗을 교체
        if left > right:
            array[right], array[pivot] = array[pivot], array[right]
        # 아니면 작은 값과 큰 값을 교체
        else:
            array[left], array[right] = array[right], array[left]
    
    # 분할 이후 왼쪽, 오른쪽에 대해 각각 수행
    quick_sort(array, start, right-1)
    quick_sort(array, right+1, end)

quick_sort(array, 0, n-1)

print("정렬: ", array)