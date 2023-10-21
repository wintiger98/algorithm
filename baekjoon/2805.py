import sys

input = sys.stdin.readline

n, m = map(int, input().split())
trees = list(map(int, input().split()))

start = 1
end = max(trees)

while start <= end:
    mid = (start + end) // 2
    height = 0
    for tree in trees:
        height += (tree - mid) if tree > mid else 0

    if height >= m:
        start = mid + 1
    else:
        end = mid - 1

print(end)
