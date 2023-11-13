n, m = map(int, input().split())
nums = list(map(int, input().split()))
sections = [list(map(int, input().split())) for _ in range(m)]

sum_array = [0] * (n + 1)

for i in range(1, n + 1):
    sum_array[i] = sum_array[i - 1] + nums[i - 1]

for section in sections:
    i, j = section

    print(sum_array[j] - sum_array[i - 1])
