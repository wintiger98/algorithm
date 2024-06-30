import sys
input = sys.stdin.readline

N = int(input())

MAX_TIME = 1_000_001
delta = [0] * MAX_TIME

for _ in range(N):
    S, E = map(int, input().split())
    delta[S] += 1
    if E + 1 < MAX_TIME:
        delta[E + 1] -= 1

# 누적 합 계산
current_seats = 0
occupied_seats = [0] * MAX_TIME

for i in range(1, MAX_TIME):
    current_seats += delta[i]
    occupied_seats[i] = current_seats

Q = int(input())
times = list(map(int, input().split()))

for t in times:
    print(occupied_seats[t])