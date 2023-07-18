n, m = map(int, input().split())
card_graph = [list(map(int, input().split())) for _ in range(n)]
mins = []
for cards in card_graph:
    mins.append(min(cards))
print(max(mins))