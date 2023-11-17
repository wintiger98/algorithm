# for t in range(1, int(input()) + 1):
#     n, m = map(int, input().split())
#     arr = list(map(int, input().split()))
#     result = []
#     for i in range(n - m + 1):
#         tmp = 0
#         for j in range(i, i + m):
#             tmp += arr[j]
#         result.append(tmp)
#     print(f"#{t} {max(result)-min(result)}")
for t in range(1, int(input()) + 1):
    n, m = map(int, input().split())
    arr = list(map(int, input().split()))

    sm = mn = mx = sum(arr[0:m])

    for i in range(m, n):
        sm += arr[i]
        sm -= arr[i - m]
        if mn > sm:
            mn = sm
        if mx < sm:
            mx = sm
    print(f"#{t} {mx-mn}")
