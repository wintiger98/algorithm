for t in range(1, int(input()) + 1):
    N = int(input())
    result = [0 for _ in range(5)]

    while N > 1:
        if N % 2 == 0:
            N //= 2
            result[0] += 1
        if N % 3 == 0:
            N //= 3
            result[1] += 1
        if N % 5 == 0:
            N //= 5
            result[2] += 1
        if N % 7 == 0:
            N //= 7
            result[3] += 1
        if N % 11 == 0:
            N //= 11
            result[4] += 1
    result = " ".join(map(str, result))
    print(f"#{t} {result}")
