c = input()
x = int(c[1])
y = ord(c[0]) - ord("a") + 1

dx = [1, 1, -1, -1, 2, 2, -2, -2]
dy = [2, -2, 2, -2, 1, -1, 1, -1]

result = 0
for i in range(len(dx)):
    cx = x + dx[i]
    cy = y + dy[i]

    if cx >= 1 and cx <= 8 and cy >= 1 and cy <= 8:
        result += 1

print(result)
