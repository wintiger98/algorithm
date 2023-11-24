from collections import deque


# def solution(rc, operations):
#     rc = deque([deque(r) for r in rc])
#     firsts = deque()
#     lasts = deque()
#     for r in rc:
#         firsts.append(r.popleft())
#         lasts.append(r.pop())

#     for operation in operations:
#         if operation == "ShiftRow":
#             rc.appendleft(rc.pop())
#             firsts.appendleft(firsts.pop())
#             lasts.appendleft(lasts.pop())
#         else:
#             rc[0].appendleft(firsts.popleft())
#             lasts.appendleft(rc[0].pop())
#             rc[-1].append(lasts.pop())
#             firsts.append(rc[-1].popleft())

#     for r in rc:
#         r.appendleft(firsts.popleft())
#         r.append(lasts.popleft())

#     return [list(r) for r in rc]


def solution(rc, operations):
    rc = deque([deque(r) for r in rc])
    firsts = deque()
    lasts = deque()

    for r in rc:
        firsts.append(r.popleft())
        lasts.append(r.pop())

    for operation in operations:
        if operation == "ShiftRow":
            rc.appendleft(rc.pop())
            firsts.appendleft(firsts.pop())
            lasts.appendleft(lasts.pop())
        else:
            rc[0].appendleft(firsts.popleft())
            lasts.appendleft(rc[0].pop())
            rc[-1].append(lasts.pop())
            firsts.append(rc[-1].popleft())

    for r in rc:
        r.appendleft(firsts.popleft())
        r.append(lasts.popleft())

    return [list(r) for r in rc]
