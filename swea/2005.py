for i in range(1, int(input()) + 1):
    n = int(input())

    print(f"#{i}")

    graph = [[]]

    graph.append([1])

    for i in range(2, n + 1):
        tmp = []
        for j in range(i):
            if j == 0 or j == i - 1:
                tmp.append(1)
            else:
                tmp.append(graph[i - 1][j - 1] + graph[i - 1][j])
        graph.append(tmp)

    for g in graph[1:]:
        print(*g)
