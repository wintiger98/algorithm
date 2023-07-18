n, m = map(int, input().split())
stack = []
visited = [False] * (n+1)

def dfs():
    if len(stack) == m:
        print(" ".join(map(str, stack)))
        return
    for i in range(1, n+1):
        if i not in stack:
            stack.append(i)
            dfs()
            stack.pop()

dfs()