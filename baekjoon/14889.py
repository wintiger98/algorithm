import sys

input = sys.stdin.readline

n = int(input())
status = [list(map(int, input().split())) for _ in range(n)]
visited = [False for _ in range(n)]

result = sys.maxsize


def backTracking(depth, idx):
    global result

    if depth == n // 2:
        start = 0
        link = 0
        for i in range(n):
            for j in range(n):
                if visited[i] and visited[j]:
                    start += status[i][j]
                elif not visited[i] and not visited[j]:
                    link += status[i][j]
        result = min(result, abs(start - link))
        return

    for i in range(idx, n):
        if not visited[i]:
            visited[i] = True
            backTracking(depth + 1, i + 1)
            visited[i] = False


backTracking(0, 0)
print(result)
