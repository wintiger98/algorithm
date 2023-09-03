n = int(input())
plans = list(input().split())

dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]
directions = ["L", "R", "U", "D"]

x, y = 1, 1
for plan in plans:
    for i in range(len(directions)):
        if plan == directions[i]:
            x += dx[i]
            y += dy[i]
            break
    if x < 1 or y < 1 or x > n or y > n:
        x -= dx[i]
        y -= dy[i]

print(x, y)
