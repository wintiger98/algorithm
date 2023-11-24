from collections import defaultdict
from heapq import heappush, heappop


# 다익스트라
def solution(n, paths, gates, summits):
    graph = defaultdict(set)
    for i, j, w in paths:
        graph[i].add((j, w))
        graph[j].add((i, w))

    intensities = [float("inf")] * (n + 1)
    heap = []
    for gate in gates:
        intensities[gate] = 0
        heappush(heap, (0, gate))

    while heap:
        # intensity, node
        i, n = heappop(heap)
        # 현재 intensity보다 이미 작거나, 정상에 도달했을 경우 stop
        if intensities[n] < i or n in summits:
            continue

        # 이웃 확인 (이웃, 이웃 intensity)
        for j, ji in graph[n]:
            ni = max(i, ji)
            # 만약 인텐시티가 더 작으면 업데이트
            if intensities[j] > ni:
                intensities[j] = ni
                heappush(heap, (ni, j))
    # summit이 리스트로 주어졌는데 순서 상관없이 존재 여부만 파악하기 때문에 시간 복잡도를 위해 set 선언
    summits = set(summits)
    # 초기화
    answer = [-1, float("inf")]
    # 앞 번호 부터 확인해야되서.
    for summit in sorted(summits):
        if intensities[summit] < answer[1]:
            answer = [summit, intensities[summit]]
    return answer
