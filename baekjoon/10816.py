n = int(input())
cards = list(map(int, input().split()))
cards.sort()

m = int(input())
targets = list(map(int, input().split()))

count_dict = {}

for card in cards:
    if card in count_dict:
        count_dict[card] += 1
    else:
        count_dict[card] = 1

for target in targets:
    if target in count_dict:
        print(count_dict[target], end=" ")
    else:
        print(0, end=" ")
