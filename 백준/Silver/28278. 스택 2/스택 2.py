import sys
input = sys.stdin.readline

N = int(input())

stack = []

for _ in range(N):
    orders = list(map(int, input().split()))
    if orders[0] == 1:
        stack.append(orders[1])
    elif orders[0] == 2:
        if stack:
            print(stack.pop())
        else:
            print(-1)
    elif orders[0] == 3:
        print(len(stack))
    elif orders[0] == 4:
        if stack:
            print(0)
        else:
            print(1)
    else:
        if stack:
            print(stack[-1])
        else:
            print(-1)