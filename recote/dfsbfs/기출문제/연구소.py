n, m = map(int, input().split())
data = [list(map(int, input().split())) for _ in range(n)]
# 벽 설치 뒤 데이터
tmp = [[0] * m for _ in range(n)]

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

result = 0


def virus(x, y):
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]

        if nx >= 0 and nx < n and ny >= 0 and ny < m:
            if tmp[nx][ny] == 0:
                tmp[nx][ny] = 2
                virus(nx, ny)


def get_score():
    score = 0
    for i in range(n):
        for j in range(m):
            if tmp[i][j] == 0:
                score += 1
    return score


def dfs(count):
    global result
    # 울타리 개수가 세개면
    if count == 3:
        # 현재 맵에 데이터 옮기기
        for i in range(n):
            for j in range(m):
                tmp[i][j] = data[i][j]
        # 바이러스 뿌리기
        for i in range(n):
            for j in range(m):
                if tmp[i][j] == 2:
                    virus(i, j)
        result = max(result, get_score())
        return
    # 울타리 세우기
    for i in range(n):
        for j in range(m):
            if data[i][j] == 0:
                data[i][j] = 1
                count += 1
                dfs(count)
                # dfs 완료했으면 카운트 줄이고 벽 취소
                data[i][j] = 0
                count -= 1


dfs(0)
print(result)
