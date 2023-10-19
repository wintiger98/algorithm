graph = []
max_value = 0
for i in range(9):
    row = list(map(int, input().split()))
    tmp_max = max(row)
    if max_value < tmp_max:
        max_value = tmp_max
    graph.append(row)

is_finish = False
for i in range(9):
    for j in range(9):
        if graph[i][j] == max_value:
            print(max_value)
            print(i + 1, j + 1)
            is_finish = True
            break
    if is_finish:
        break
