# 수열에서 n개 뽑는 방법
def gen_combinations(arr, n):
    result = []

    if n == 0:
        return [[]]

    for i in range(0, len(arr)):
        elem = arr[i]
        rest_arr = arr[i + 1 :]
        for C in gen_combinations(rest_arr, n - 1):
            result.append([elem] + C)

    return result
