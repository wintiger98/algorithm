arr = [3, 30, 34, 5, 9]
arr = list(map(str, arr))
arr = sorted(arr, key=lambda x: x[0], reverse=True)
print(arr)
