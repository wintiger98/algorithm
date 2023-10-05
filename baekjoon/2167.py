import sys

input = sys.stdin.readline

n, m = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(2)]

k = int(input())
ijxys = [list(map(int, input().split())) for _ in range(k)]
result = []
for ijxy in ijxys:
    i, j, x, y = ijxy
    tmp_sum = 0
    for tmp_x in range(i, x + 1):
        for tmp_y in range(j, y + 1):
            tmp_sum += graph[tmp_x - 1][tmp_y - 1]
    result.append(tmp_sum)
for r in result:
    print(r)
