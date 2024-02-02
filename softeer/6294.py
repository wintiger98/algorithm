import sys

input = sys.stdin.readline

n, k = map(int, input().split())
arr = list(map(int, input().split()))

prefix_sum = [0 for _ in range(n + 1)]

for i in range(1, n + 1):
    prefix_sum[i] = prefix_sum[i - 1] + arr[i - 1]

for _ in range(k):
    i, j = map(int, input().split())
    print("{:.2f}".format((prefix_sum[j] - prefix_sum[i - 1]) / (j - i + 1)))
