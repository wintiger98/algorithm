n, m = map(int, input().split())
cards = [list(map(int, input().split())) for _ in range(n)]

result = 0
for card in cards:
    result = min(card) if result < min(card) else result

print(result)
