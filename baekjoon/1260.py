import sys
from collections import deque

input = sys.stdin.readline

n, m, v = map(int, input().split())
graph = [[] for _ in range(n + 1)]

for _ in range(m):
    i, j = map(int, input().split())
    graph[i].append(j)
    graph[j].append(i)

for i in range(n + 1):
    graph[i] = sorted(graph[i])

visited_dfs = [False for _ in range(n + 1)]
result_dfs = []

visited_bfs = [False for _ in range(n + 1)]
result_bfs = []


def dfs(start):
    visited_dfs[start] = True
    result_dfs.append(start)

    for i in graph[start]:
        if not visited_dfs[i]:
            dfs(i)


def bfs(start):
    queue = deque()
    queue.append(start)
    visited_bfs[start] = True

    while queue:
        v = queue.popleft()
        result_bfs.append(v)

        for i in graph[v]:
            if not visited_bfs[i]:
                queue.append(i)
                visited_bfs[i] = True


dfs(v)
print(*result_dfs)

bfs(v)
print(*result_bfs)
