import sys

input = sys.stdin.readline
import heapq

heap = []

for _ in range(int(input())):
    x = int(input())

    if x != 0:
        heapq.heappush(heap, (abs(x), x))
    else:
        if heap:
            print(heapq.heappop(heap)[1])
        else:
            print(0)
