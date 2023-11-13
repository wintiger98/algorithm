# 숫자 카드 게임
n, m = map(int, input().split())
cards = [list(map(int, input().split())) for _ in range(n)]
answer = 0
for card in cards:
    if min(card) > answer:
        answer = min(card)

print(answer)