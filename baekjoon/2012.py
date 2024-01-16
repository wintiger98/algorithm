import sys

input = sys.stdin.readline

n = int(input())
students = []
for _ in range(n):
    students.append(int(input()))

students.sort()

ans = 0

for i in range(len(students)):
    ans += abs(students[i] - (i + 1))

print(ans)
