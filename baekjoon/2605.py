N = int(input())
lst = list(map(int, input().split()))
alst = [i for i in range(1, N + 1)]

for i in range(N):
    # 이동 칸수, 학생 번호
    n, t = lst[i], alst[i]
    for j in range(i, i - n - 1, -1):
        alst[j] = alst[j - 1]
    alst[i - n] = t

print(*alst)
