import sys

input = sys.stdin.readline

n = int(input())
m = int(input())
graph = [[] for _ in range(n + 1)]

ans = 0

for _ in range(m):
    i, j = map(int, input().split())
    graph[i].append(j)
    graph[j].append(i)

visited = [False] * (n + 1)

def dfs(v):
    global ans
    visited[v] = True
    ans += 1

    for i in graph[v]:
        if not visited[i]:
            dfs(i)


dfs(1)

print(ans - 1)
