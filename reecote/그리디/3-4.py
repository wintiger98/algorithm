# 1이 될 때까지
n, k = map(int, input().split())
answer = 0

while True:
    target = (n//k) * k
    answer += (n-target)
    n = target

    if n<k:
        break
    answer += 1
    n //= k

answer += (n-1)

print(answer)