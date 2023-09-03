n = int(input())
all = list(map(int, input().split()))
all.sort()

m = int(input())
todos = list(map(int, input().split()))


def binary_search(array, target, start, end):
    while start <= end:
        mid = (start + end) // 2
        if array[mid] == target:
            return "yes"
        elif array[mid] > target:
            end = mid - 1
        else:
            start = mid + 1
    return "no"


for todo in todos:
    print(binary_search(all, todo, 0, n), end=" ")
