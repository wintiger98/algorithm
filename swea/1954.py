dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

for t in range(1, int(input()) + 1):
    n = int(input())
    graph = [[0] * n for _ in range(n)]

    cur_d = 0
    x, y = 0, 0

    for i in range(1, n * n + 1):
        graph[x][y] = i
        x += dx[cur_d]
        y += dy[cur_d]

        if x < 0 or x >= n or y < 0 or y >= n or graph[x][y] != 0:
            x -= dx[cur_d]
            y -= dy[cur_d]

            cur_d = (cur_d + 1) % 4

            x += dx[cur_d]
            y += dy[cur_d]

    print(f"#{t}")
    for g in graph:
        print(*g)
