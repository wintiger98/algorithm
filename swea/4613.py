for test_case in range(1, int(input()) + 1):
    N, M = map(int, input().split())
    arr = [input() for _ in range(N)]

    mx = 0

    for i in range(N - 2):
        for j in range(i, N - 1):
            cnt = 0
            for s in range(i + 1):
                cnt += arr[s].count("W")
            for s in range(i + 1, j + 1):
                cnt += arr[s].count("B")
            for s in range(j + 1, N):
                cnt += arr[s].count("R")
            mx = max(mx, cnt)
    print(f"#{test_case} {N*M-mx}")
