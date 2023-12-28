dx_plus = [0, 0, 1, -1]
dy_plus = [1, -1, 0, 0]
dx_x = [1, 1, -1, -1]
dy_x = [1, -1, 1, -1]


def kill_fly_plus(x, y):
    global answer
    tmp = arr[x][y]
    for m in range(1, M):
        for i in range(4):
            cx = x + dx_plus[i] * m
            cy = y + dy_plus[i] * m
            if 0 <= cx < N and 0 <= cy < N:
                tmp += arr[cx][cy]
    if answer < tmp:
        answer = tmp


def kill_fly_x(x, y):
    global answer
    tmp = arr[x][y]
    for m in range(1, M):
        for i in range(4):
            cx = x + dx_x[i] * m
            cy = y + dy_x[i] * m
            if 0 <= cx < N and 0 <= cy < N:
                tmp += arr[cx][cy]
    if answer < tmp:
        answer = tmp


for test_case in range(1, int(input()) + 1):
    N, M = map(int, input().split())
    arr = [list(map(int, input().split())) for _ in range(N)]
    answer = 0
    for i in range(N):
        for j in range(N):
            kill_fly_plus(i, j)
            kill_fly_x(i, j)

    print(f"#{test_case} {answer}")
