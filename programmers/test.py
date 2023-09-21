n = int(input())
answer = 0
for i in range(n):
    for j in range(1, n):
        if i + j < n:
            answer += (n - (i + j)) ** 2
print(answer)
