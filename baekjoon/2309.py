from collections import deque

heights = []
for _ in range(9):
    heights.append(int(input()))


def solve():
    num = sum(heights) - 100
    N = 9
    # n개 중에서 2개를 뽑는 모든 조합
    for i in range(N - 1):
        for j in range(i + 1, N):
            if heights[i] + heights[j] == num:
                return heights[i], heights[j]


n, m = solve()  # 7명에 포함되지 않는 2명 찾기
for i in sorted(heights):
    if i != n and i != m:
        print(i)
