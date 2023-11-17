for t in range(1, int(input()) + 1):
    n, q = map(int, input().split())
    # works = [list(map(int, input().split())) for _  in range(q)]
    works = []
    result = [0 for _ in range(n + 1)]

    for i in range(1, q + 1):
        l, r = map(int, input().split())
        works.append([i, l, r])

    for work in works:
        i, l, r = work
        result[l : r + 1] = [i for _ in range(r - l + 1)]
    result = " ".join(map(str, result[1:]))
    print(f"#{t} {result}")
