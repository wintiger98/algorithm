text = input()
x = int(text[-1])
y = ord(text[0]) - ord("a") + 1

dx = [-2, -2, 2, 2, -1, -1, 1, 1]
dy = [1, -1, 1, -1, 2, -2, 2, -2]
result = 0
for i in range(len(dx)):
    cx = x + dx[i]
    cy = y + dy[i]
    if cx < 1 or cy < 1 or cx > 8 or cy > 8:
        continue
    else:
        result += 1

print(result)
