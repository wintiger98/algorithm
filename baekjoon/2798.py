from collections import deque

n, m = map(int, input().split())
cards = list(map(int, input().split()))

queue = deque()
queue.append((cards[0], 0, 1))
queue.append((0, 0, 0))

answer = 0
new_cards = []
for i in range(n):
    if cards[i] < m:
        new_cards.append(cards[i])

n = len(new_cards)
cards = new_cards

while queue:
    sm, idx, cnt = queue.popleft()
    # m 넘으면 스탑
    if sm > m:
        continue
    # 세장
    if cnt == 3:
        if answer < sm:
            answer = sm
        continue
    idx += 1
    if idx == n:
        continue
    # 선택
    queue.append((sm + cards[idx], idx, cnt + 1))
    # 선택X
    queue.append((sm, idx, cnt))

print(answer)
