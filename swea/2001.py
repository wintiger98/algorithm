for t in range(1, int(input()) + 1):
    n, m = map(int, input().split())
    area = [list(map(int, input().split())) for _ in range(n)]

    ret = [[0] * (n - m + 1) for _ in range(n - m + 1)]

    for i in range(n - m + 1):
        for j in range(n - m + 1):
            tmp_sum = 0
            for a in range(m):
                for b in range(m):
                    tmp_sum += area[i + a][j + b]
            ret[i][j] = tmp_sum
    result = 0
    for r in ret:
        max_r = max(r)
        if max_r > result:
            result = max_r
    print(f"#{t} {result}")
