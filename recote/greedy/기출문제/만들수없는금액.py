n = int(input())
coins = list(map(int, input().split()))
coins.sort()
num = 1

for coin in coins:
    if coin > num:
        break
    num += coin

print(num)
