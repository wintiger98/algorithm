import sys

input = sys.stdin.readline

n = int(input())
cases = list(map(int, input().split()))

arr = [0]

for case in cases:
    if arr[-1] < case:
        arr.append(case)
    else:
        left = 0
        right = len(arr)

        while left < right:
            mid = (left + right) // 2

            if cases[mid] < case:
                left = mid + 1
            else:
                right = mid - 1
        arr[right] = case

print(len(arr) - 1)
