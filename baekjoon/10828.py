import sys
input = sys.stdin.readline

n = int(input())

stack = []

def stack_pop():
    return stack.pop() if len(stack) else -1

def stack_size():
    return len(stack)

def stack_empty():
    return 0 if len(stack) else 1

def stack_top():
    return stack[-1] if len(stack) else -1

operations = {
    "pop":stack_pop,
    "size":stack_size,
    "empty":stack_empty,
    "top":stack_top,
}

for _ in range(n):
    operation = input().split()
    if operation[0] == 'push':
        stack.append(int(operation[1]))
    else:
        result = operations[operation[0]]()
        print(result)
