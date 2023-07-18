import sys
input = sys.stdin.readline

k = int(input())
stack = []
for _ in range(k):
    tmp = int(input())
    if tmp == 0:
        stack.pop()
    else:
        stack.append(tmp)
print(sum(stack))