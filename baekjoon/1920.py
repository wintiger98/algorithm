n = int(input())
a = list(map(int, input().split()))
m = int(input())
nums = list(map(int, input().split()))

a.sort()


def binary_search(array, target, start, end):
    while start <= end and start < n:
        mid = (start + end) // 2
        if array[mid] == target:
            return 1
        elif array[mid] > target:
            end = mid - 1
        else:
            start = mid + 1
    return 0


for num in nums:
    print(binary_search(a, num, 0, n))
