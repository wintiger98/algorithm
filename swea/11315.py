def solve():
    for si in range(n):
        for sj in range(n):
            for di, dj in ((0, 1), (1, 0), (1, 1), (-1, 1)):
                for mul in range(5):
                    ni, nj = si + di * mul, sj + dj * mul

                    if 0 <= ni < n and 0 <= nj < n and arr[ni][nj] == "o":
                        pass
                    else:
                        break
                else:
                    return "YES"
    return "NO"


for test_case in range(1, int(input()) + 1):
    n = int(input())
    arr = [input() for _ in range(n)]

    ans = solve()

    print(f"#{test_case} {ans}")
