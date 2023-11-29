n = int(input())
arr = [[0] * 30 for _ in range(30)]

for _ in range(n):
    # 해당 영역을 1로 표시
    sj, si = map(int, input().split())
    for i in range(si, si + 10):
        for j in range(sj, sj + 10):
            if i == si or i == si + 9 or j == sj or j == sj + 9:
                arr[i][j] = 1
            else:
                arr[i][j] = 0


ans = 0
for lst in arr:
    print(*lst)
    ans += sum(lst)
print(ans)
