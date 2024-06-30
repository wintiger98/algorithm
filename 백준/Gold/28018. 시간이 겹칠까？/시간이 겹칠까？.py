import sys
input = sys.stdin.readline

N = int(input())

dp = [0]*1_000_001
in_times = [0]*1_000_001 # 들어온 시각
out_times = [0]*1_000_001 # 종료 시각

for _ in range(N):
    S, E = map(int, input().split())
    in_times[S] += 1
    out_times[E] += 1

for i in range(1, 1_000_001):
    dp[i] = dp[i-1] + in_times[i] - out_times[i-1] # 종료되는 시각에 사용 못해서

Q = int(input())
times = list(map(int, input().split()))

for t in times:
    print(dp[t])