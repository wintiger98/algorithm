for test_case in range(1, int(input()) + 1):
    E, N = map(int, input().split())
    lst = list(map(int, input().split()))
    graph = [[] for _ in range(E + 2)]

    for i in range(0, 2 * E, 2):
        graph[lst[i]].append(lst[i + 1])

    for i in range(1, E + 2):
        if N in graph[i]:
            for j in range(len(graph[i][:])):
                if N == graph[i][j]:
                    graph[i].pop(j)
                    break

    visited = [False] * (E + 2)
    answer = [0]

    def dfs(v):
        visited[v] = True
        answer[0] += 1

        for i in graph[v]:
            if not visited[i]:
                dfs(i)

    dfs(N)

    print(f"#{test_case} {answer[0]}")
