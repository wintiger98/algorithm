n = int(input())
horors = list(map(int, input().split()))
horors.sort()

result = 0
count = 0

for horor in horors:
    count += 1
    if count >= horor:
        result += 1
        count = 0

print(result)
