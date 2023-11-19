def dfs(n, sm, cnt):
    global answer
    if cnt > N or sm > K:
        return

    if n == 12:
        if cnt == N and sm == K:
            answer += 1
        return

    dfs(n + 1, sm + lst[n], cnt + 1)

    dfs(n + 1, sm, cnt)


for test_case in range(1, int(input()) + 1):
    N, K = map(int, input().split())
    lst = [n for n in range(1, 13)]
    answer = 0
    dfs(0, 0, 0)
    print(f"#{test_case} {answer}")
