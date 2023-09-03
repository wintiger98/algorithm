n = int(input())

data = []
for _ in range(n):
    person, score = input().split()
    score = int(score)
    data.append((person, score))

result = sorted(data, key=lambda x: x[1])
for i in result:
    print(i[0], end=" ")
