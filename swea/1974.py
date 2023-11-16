def check_graph(graph):
    for row in graph:
        if sorted(row) != [1, 2, 3, 4, 5, 6, 7, 8, 9]:
            return False
    return True


for i in range(1, int(input()) + 1):
    graph = [list(map(int, input().split())) for _ in range(9)]

    print(f"#{i}", end=" ")

    answer = check_graph(graph)
    if answer == False:
        print(0)
        continue

    graph = list(map(list, zip(*graph)))

    answer = check_graph(graph)
    if answer == False:
        print(0)
        continue

    is_done = False
    for i in range(3):
        for j in range(3):
            tmp_nums = []
            for n in range(3 * i, 3 * i + 3):
                for m in range(3 * j, 3 * j + 3):
                    tmp_nums.append(graph[n][m])
            if sorted(sorted(tmp_nums)) != [1, 2, 3, 4, 5, 6, 7, 8, 9]:
                is_done = True
                break
        if is_done:
            break
    if is_done == True:
        print(0)
    else:
        print(1)
