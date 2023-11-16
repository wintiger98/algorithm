def rotate_90(graph):
    n = len(graph)
    result = [[0] * n for _ in range(n)]

    for r in range(n):
        for c in range(n):
            result[c][n - 1 - r] = graph[r][c]
    return result


def rotate_180(graph):
    n = len(graph)
    result = [[0] * n for _ in range(n)]

    for r in range(n):
        for c in range(n):
            result[n - 1 - r][n - 1 - c] = graph[r][c]
    return result


def rotate_270(graph):
    n = len(graph)
    result = [[0] * n for _ in range(n)]

    for r in range(n):
        for c in range(n):
            result[n - 1 - c][r] = graph[r][c]
    return result


for i in range(1, int(input()) + 1):
    n = int(input())
    graph = [list(map(int, input().split())) for _ in range(n)]
    print(f"#{i}")

    one = rotate_90(graph)
    two = rotate_180(graph)
    three = rotate_270(graph)

    for i in range(n):
        result = ""
        result += "".join(list(map(str, one[i])))
        result += " "
        result += "".join(list(map(str, two[i])))
        result += " "
        result += "".join(list(map(str, three[i])))
        print(result)
