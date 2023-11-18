for _ in range(10):
    t = int(input())
    array = [list(map(int, input().split())) for _ in range(100)]

    max_value = 0

    # 행
    for a in array:
        tmp_value = sum(a)
        if max_value < tmp_value:
            max_value = tmp_value

    # 열
    array = list(map(list, zip(*array)))
    for a in array:
        tmp_value = sum(a)
        if max_value < tmp_value:
            max_value = tmp_value

    # 대각선
    line1 = 0
    line2 = 0
    for i in range(100):
        for j in range(100):
            if i == j:
                line1 += array[i][j]
            if i + j == 99:
                line2 += array[i][j]
    print(f"#{t} {max(max_value, line1, line2)}")
