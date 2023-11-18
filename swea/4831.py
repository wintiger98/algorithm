def check_stop(cur, k):
    for stop in stops[::-1]:
        if stop > cur and stop <= cur + k:
            return stop
    return 0


for t in range(1, int(input()) + 1):
    k, n, m = map(int, input().split())
    stops = list(map(int, input().split()))

    cur = 0
    cnt = 0

    while True:
        cur = check_stop(cur, k)
        if cur == 0:
            cnt = 0
            break
        cnt += 1
        if cur + k >= n:
            break

    print(f"#{t} {cnt}")
