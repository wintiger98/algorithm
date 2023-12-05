N = 5
arr = [list(map(int, input().split())) for _ in range(N)]

nums = []
for _ in range(N):
    nums += list(map(int, input().split()))


def check_row(arr):
    cnt = 0
    for row in arr:
        if sum(row) == 0:
            cnt += 1
    return cnt


def check_cross(arr):
    sum1 = 0
    sum2 = 0
    for i in range(N):
        for j in range(N):
            if i == j:
                sum1 += arr[i][j]
            if i + j == N - 1:
                sum2 += arr[i][j]
    cnt = 0
    if sum1 == 0:
        cnt += 1
    if sum2 == 0:
        cnt += 1
    return cnt


for idx, num in enumerate(nums):
    for i in range(N):
        for j in range(N):
            if arr[i][j] == num:
                arr[i][j] = 0

    if check_row(arr) + check_row(list(map(list, zip(*arr)))) + check_cross(arr) >= 3:
        break

print(idx + 1)
