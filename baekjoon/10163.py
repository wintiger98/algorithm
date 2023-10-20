n = int(input())
arrays = [[-1] * 1001 for _ in range(1001)]

for i in range(n):
    x, y, weight, height = map(int, input().split())
    for w in range(weight):
        # 한번에 바꾸기. 이래야 시간초과 안뜸
        arrays[x+w][y:y+height] = [i]*height

for i in range(n):
    result = 0
    for cnt in range(1001):
        result += arrays[cnt].count(i)
    print(result)