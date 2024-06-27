import sys
from collections import defaultdict
input = sys.stdin.readline

N, M = map(int, input().split())

nums = list(map(int, input().split()))

current_sum = 0
answer = 0
count_map = defaultdict(int)
count_map[0] = 1

for num in nums:
    current_sum += num
    if current_sum - M in count_map:
        answer += count_map[current_sum - M]
    count_map[current_sum] += 1

print(answer)