for test_case in range(1, int(input()) + 1):
    N, M = map(int, input().split())
    a = list(map(int, input().split()))
    b = list(map(int, input().split()))
    answer = 0

    if len(a) > len(b):
        a, b = b, a

    for start in range(len(b) - len(a) + 1):
        tmp = 0

        for j in range(len(a)):
            tmp += a[j] * b[start + j]
        answer = max(tmp, answer)

    print(f"#{test_case} {answer}")
