n = int(input())

result = 0
coins = [500, 100, 50, 10]

for coin in coins:
    result += n // coin
    n %= coin

print(result)
