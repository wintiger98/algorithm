def dfs(cur, visited):
    global answer
    answer = max(answer, len(visited))

    for n in graph[cur]:
        if n not in visited:
            dfs(n, visited + [n])


for test_case in range(1, int(input()) + 1):
    N, M = map(int, input().split())
    answer = 0

    graph = [[] for _ in range(N + 1)]
    for _ in range(M):
        x, y = map(int, input().split())
        graph[x].append(y)
        graph[y].append(x)

    for s in range(1, N + 1):
        dfs(s, [s])

    print(f"#{test_case} {answer}")
