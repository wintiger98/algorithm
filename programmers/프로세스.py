from collections import deque


def solution(priorities, location):
    answer = 0
    processes = [(i, priorities[i]) for i in range(len(priorities))]

    queue = deque(processes)
    while queue:
        idx, priority = queue.popleft()

        if any(priority < q[1] for q in queue):
            queue.append((idx, priority))
        else:
            answer += 1
            if idx == location:
                return answer
