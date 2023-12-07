# 1
# 4 12
# 1 2 1
# 1 1 2
# 4 3 1
# 4 4 2
# 2 1 1
# 4 2 2
# 3 4 1
# 1 3 2
# 2 4 1
# 1 4 2
# 4 1 2
# 3 1 2
for test_case in range(1, int(input()) + 1):
    N, M = map(int, input().split())

    arr = [[0] * (N + 1) for _ in range(N + 1)]

    arr[N // 2][N // 2] = 2
    arr[N // 2 + 1][N // 2] = 1
    arr[N // 2 + 1][N // 2 + 1] = 2
    arr[N // 2][N // 2 + 1] = 1

    for _ in range(M):
        si, sj, d = map(int, input().split())

        arr[si][sj] = d

        for di, dj in (
            (-1, -1),
            (-1, 0),
            (-1, 1),
            (1, -1),
            (1, 0),
            (1, 1),
            (0, -1),
            (0, 1),
        ):
            r = []
            for mul in range(1, N):
                ni, nj = si + di * mul, sj + dj * mul
                if 1 <= ni <= N and 1 <= nj <= N:
                    # 0이면 비어있으니 끝
                    if arr[ni][nj] == 0:
                        break
                    # 같은 색깔이면 그 전까지 뒤집기
                    elif arr[ni][nj] == d:
                        while r:
                            ti, tj = r.pop()
                            arr[ti][tj] = d
                        break
                    # 다른 색깔이면 r에 투입
                    else:
                        r.append((ni, nj))
                else:
                    break

    ans = [0, 0]
    for row in arr:
        ans[0] += row.count(1)
        ans[1] += row.count(2)
    print(f"#{test_case} {ans[0]} {ans[1]}")
