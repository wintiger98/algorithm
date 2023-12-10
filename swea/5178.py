for test_case in range(1, int(input()) + 1):
    N, M, L = map(int, input().split())

    arr = [0 for _ in range(N + 1)]
    start = int(1e9)
    for _ in range(M):
        n, value = map(int, input().split())
        arr[n] = value
        start = min(start, n)

    for i in range(start - 1, 0, -1):
        tmp = arr[i * 2]
        if N >= i * 2 + 1:
            tmp += arr[i * 2 + 1]
        arr[i] = tmp

    print(f"#{test_case} {arr[L]}")
