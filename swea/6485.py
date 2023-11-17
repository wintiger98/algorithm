for t in range(1, int(input()) + 1):
    n = int(input())
    abab = [list(map(int, input().split())) for _ in range(n)]

    p = int(input())
    cc = []
    for _ in range(p):
        cc.append(int(input()))
    result = [0 for _ in range(p)]

    for ab in abab:
        a, b = ab
        for i, c in enumerate(cc):
            if c in [i for i in range(a, b + 1)]:
                result[i] += 1

    print(f"#{t}", *result)
