import sys
input = sys.stdin.readline

times = []

for _ in range(int(input())):
    start, end = map(int, input().split())
    times.append((end, start))

times.sort()

answer = 0
last_end = 0

for end, start in times:
    if last_end <= start:
        answer += 1
        last_end = end

print(answer)