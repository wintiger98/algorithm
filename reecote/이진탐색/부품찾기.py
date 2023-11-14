n = int(input())
products = list(map(int, input().split()))

m = int(input())
targets = list(map(int, input().split()))


def binary_search(array, target, start, end):
    while start <= end:
        mid = (start + end) // 2
        if array[mid] == target:
            return mid
        elif array[mid] > target:
            end = mid - 1
        else:
            start = mid + 1
    return None


for target in targets:
    if binary_search(products, target, 0, len(products) - 1):
        print("yes")
    else:
        print("no")
