for _ in range(10):
    t = int(input())
    arr = [[0] + list(map(int, input().split())) + [0] for _ in range(100)]

    # 2값인 시작위치 찾기(ci, cj)
    ci = 99
    for j in range(100):
        if arr[ci][j] == 2:
            cj = j
            break

    # 0행까지 올라가면서 1.(좌/우), 2.위
    while ci > 0:
        arr[ci][cj] = 0
        # 왼쪽 길
        if arr[ci][cj - 1] == 1:
            cj -= 1
        elif arr[ci][cj + 1] == 1:
            cj += 1
        else:
            ci -= 1

    print(f"#{t} {cj-1}")
