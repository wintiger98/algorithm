for test_case in range(1, int(input()) + 1):
    n = int(input())
    time_table = [list(map(int, input().split())) for _ in range(n)]

    time_table.sort(key=lambda x: x[1])
    now = 0
    answer = 0
    for s, e in time_table:
        if now <= s:
            answer += 1
            now = e

    print(f"#{test_case} {answer}")
