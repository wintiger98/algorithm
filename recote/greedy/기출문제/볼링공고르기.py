n, m = map(int, input().split())
balls = list(map(int, input().split()))

weights = [0] * 11
for ball in balls:
    weights[ball] += 1

result = 0

for i in range(n):
    n -= weights[i]
    result += weights[i] * n

print(result)
