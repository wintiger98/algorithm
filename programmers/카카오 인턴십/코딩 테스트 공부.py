def solution(alp, cop, problems):
    max_alp = max_cop = 0
    for alp_req, cop_req, _, _, _ in problems:
        max_alp = max(max_alp, alp_req)
        max_cop = max(max_cop, cop_req)
    # dp. 0~max_alp 인데 코드상 편의를 위해 max_alp + 1
    times = [[float("inf") for _ in range(max_cop + 1)] for _ in range(max_alp + 1)]
    # 최대 필요 alp, cop이 이미 갖고있는 것보다 작을 수 있으니 -> 밑의 로직 index error 방지를 위해
    alp = min(alp, max_alp)
    cop = min(cop, max_cop)
    # 처음에 갖고있는 alp, cop 에 대해선 시간을 안써도 되니 0
    times[alp][cop] = 0
    for a in range(alp, max_alp + 1):
        for c in range(cop, max_cop + 1):
            # index error 방지
            if a + 1 <= max_alp:
                times[a + 1][c] = min(times[a + 1][c], times[a][c] + 1)
            if c + 1 <= max_cop:
                times[a][c + 1] = min(times[a][c + 1], times[a][c] + 1)
            for alp_req, cop_req, alp_rwd, cop_rwd, cost in problems:
                if a >= alp_req and c >= cop_req:
                    # index 에러 방지
                    na, nc = min(a + alp_rwd, max_alp), min(c + cop_rwd, max_cop)
                    times[na][nc] = min(times[na][nc], times[a][c] + cost)
    return times[-1][-1]


# dp[i][j] -> dp[i+di][j+dj] +
