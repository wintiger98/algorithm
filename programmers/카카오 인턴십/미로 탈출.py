from heapq import heappush, heappop
from collections import defaultdict


def solution(n, start, end, roads, traps):
    inf = int(1e9)
    traps = set(traps)

    graph = defaultdict(list)

    # 이웃 노드, cost, 뒤집혔을 때 유효 여부
    for i, j, w in roads:
        graph[i].append((j, w, False))
        graph[i].append((j, w, True))

    # 함정 노드 뒤집혔는지 여부
    flipped = [False] * (n + 1)

    heap = []
    heappush(heap, (0, start, flipped))

    # 노드 상태 : (노드 번호, 노드 뒤집혔는지, 그 노드의 이웃이 뒤집혔는지)
    start_state = {start, False, tuple([False] * len(graph[start]))}
    dist = {}
    dist[start_state] = 0

    while heap:
        d, u, flipped = heappop(heap)

        u_state = {u, flipped[u], tuple(flipped[v] for v, _, _ in graph[u])}
        if d > dist.get(u_state, inf):
            continue

        for v, w, valid_when_flipped in graph[u]:
            # u-v edge가 뒤집혔는지 판단
            # 둘다 안뒤집혔거나, 뒤집혔으면 edge는 그대로
            edge_flipped = flipped[u] ^ flipped[v]

            # valid when flipped가 false라면,
            # u, v 둘다 뒤집혔거나 안뒤집혔을 때 유효
            # True라면
            # 하나만 뒤집혔을 때 유효
            if (valid_when_flipped is False and not edge_flipped) or (
                valid_when_flipped is True and edge_flipped
            ):
                f = flipped[:]
                if v in traps:
                    f[v] = not f[v]
                v_state = (v, f[v], tuple(flipped[x] for x, _, _ in graph[v]))
                if d + w < dist.get(v_state, inf):
                    dist[v_state] = d + w
                    heappush(heap, (dist[v_state], v, f))

    candidates = [dist[k] for k in dist if k[0] == end]

    return min(candidates)
