# 상하좌우
n = int(input())
moves = list(map(str, input().split()))

x, y = 1, 1

for move in moves:
    if move == "L":
        cx = x
        cy = y - 1
    elif move == "R":
        cx = x
        cy = y + 1
    elif move == "U":
        cx = x - 1
        cy = y
    else:
        cx = x + 1
        cy = y

    if not (cx < 1 or cy < 1 or cx >= n or cy >= n):
        x = cx
        y = cy

print(x, y)
