import sys
input = sys.stdin.readline

n = int(input())
parts = list(map(int, input().split()))

m = int(input())
orders = list(map(int, input().split()))

parts.sort()

def binary_search(start, end, target):
    while start <= end:
        mid = (start + end) // 2
        if parts[mid] == target:
            return "yes"
        elif parts[mid] < target:
            start = mid + 1
        else:
            end = mid - 1
    return "no"

result = []
for order in orders:
    result.append(binary_search(0, n-1, order))

print(result)