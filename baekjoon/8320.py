from math import sqrt


N = int(input())


def check(num):
    cnt = 0

    for i in range(1, int(sqrt(num)) + 1):
        if num % i == 0:
            cnt += 1

    return cnt


answer = 0
for i in range(1, N + 1):
    answer += check(i)

print(answer)
