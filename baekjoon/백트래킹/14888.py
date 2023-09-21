from collections import deque
import copy

n = int(input())
arr = list(map(int, input().split()))
op_count = list(map(int, input().split()))
ops = ["+", "-", "*", "//"]
answer = []


def bfs():
    queue = deque()
    queue.append((arr[0], 0, [0, 0, 0, 0]))
    while queue:
        num, idx, use = queue.popleft()
        idx += 1
        if idx < len(arr):
            for i in range(4):
                if op_count[i] > use[i]:
                    tmp_use = copy.deepcopy(use)
                    tmp_use[i] += 1
                    if i == 3 and num < 0:
                        tmp_num = (-1) * num // arr[idx] * (-1)
                    else:
                        tmp_num = eval(f"{num}{ops[i]}{arr[idx]}")
                    queue.append((tmp_num, idx, tmp_use))
        else:
            answer.append(num)


bfs()

print(max(answer))
print(min(answer))
