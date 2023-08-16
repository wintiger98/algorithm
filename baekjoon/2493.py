n = int(input())
towers = list(map(int, input().split()))
# 최대값 저장 스택
stack = []
# 정답
answer = []

for i in range(n):
    while stack:
        if stack[-1][1] > towers[i]:
            answer.append(stack[-1][0] + 1)
            break
        else:
            stack.pop()
    if not stack:
        answer.append(0)
    stack.append([i, towers[i]])

print(" ".join(map(str, answer)))
