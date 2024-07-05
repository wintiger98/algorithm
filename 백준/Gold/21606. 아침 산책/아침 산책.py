import sys
input = sys.stdin.readline

N = int(input())
arr = input() # 1이면 실내, 0이면 실외
in_points = [idx+1 for idx, a in enumerate(arr) if a == "1"] # 실내

graph = [[] for _ in range(N+1)]
for _ in range(N-1):
    u, v = map(int, input().split())
    graph[u].append(v)
    graph[v].append(u)

answer = 0

def dfs(v, visited):
    global answer
    for node in graph[v]:
        if visited[node]:
            continue
        if node in in_points:
            answer += 1
            continue
        visited[node] = True
        dfs(node, visited)
        visited[node] = False

for point in in_points:
    visited = [False]*(N+1)
    visited[point] = True
    dfs(point, visited)

print(answer)