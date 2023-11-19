for test_case in range(1, int(input()) + 1):
    n, m, k = map(int, input().split())
    lst = list(map(int, input().split()))

    lst.sort()

    ans = "Possible"
    cnt = 0
    for t in lst:
        cnt += 1
        if (t // m) * k < cnt:
            ans = "Impossible"
            break

    print(f"#{test_case} {ans}")
