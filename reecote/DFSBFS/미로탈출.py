from collections import deque

n, m = map(int, input().split())
graph = [list(map(int, input())) for _ in range(n)]

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

queue = deque()
queue.append((0, 0))

while queue:
    x, y = queue.popleft()

    for i in range(4):
        cx = x + dx[i]
        cy = y + dy[i]

        if cx < 0 or cx >= n or cy < 0 or cy >= m:
            continue
        if graph[cx][cy] == 1:
            graph[cx][cy] = graph[x][y] + 1
            queue.append((cx, cy))

print(graph[n - 1][m - 1])
