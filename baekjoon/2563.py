num = int(input())
xs = []
ys = []
# 가로 세로가 100, 색종이는 가로 세로가 10 -> x, y가 90을 초과한다면 잘리게됨
for _ in range(num):
    x, y = map(int, input().split())
    xs.append(x)
    ys.append(y)

result = 0

for x in range(100):
    for y in range(100):
        for k in range(num):
            if xs[k] <= x <= xs[k] + 10 and ys[k] <= y <= ys[k] + 10:
                print(f"({x},{y})는 {xs[k]}~{xs[k]+10}, {ys[k]}~{ys[k]+10}사이에 있음")
                result += 1
                break
print(result)
