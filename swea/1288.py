for test_case in range(1, int(input()) + 1):
    n = int(input())
    visited = [0 for _ in range(10)]
    answer = 1

    while sum(visited) < 10:
        tmp_n = n * answer
        for i in str(tmp_n):
            visited[int(i)] = 1
        answer += 1
    print(f"#{test_case} {n*(answer-1)}")
