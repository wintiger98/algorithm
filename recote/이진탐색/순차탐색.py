def sequential_search(n, target, array):
    for i in range(n):
        if array[i] == target[i]:
            return i + 1
