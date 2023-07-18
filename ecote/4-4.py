n, m = map(int, input().split())
a, b, d = map(int, input().split())
# d | 0: north / 1: east / 2: south / 3: west
map_graph = [list(map(int, input().split())) for _ in range(n)]


