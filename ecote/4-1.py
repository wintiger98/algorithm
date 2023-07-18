n = int(input())
moves = list(map(str, input().split()))
types = ["L","R","U","D"]
dx = [0,0,-1,1]
dy = [-1,1,0,0]
move_mapping = {
    "L":[0,-1],
    "R":[0,1],
    "U":[-1,0],
    "D":[1,0],
}
x, y = 1, 1
for move in moves:
    x += move_mapping[move][0]
    y += move_mapping[move][1]
    if x > n or x < 1 or y > n or y < 1:
        x -= move_mapping[move][0]
        y -= move_mapping[move][1]
print(x, y)