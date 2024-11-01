n, m = map(int, input().split())
board = [list(map(str, input())) for _ in range(n)]
count = []
for a in range(n - 7):
    for b in range(m - 7):
        idx1 = 0
        idx2 = 0
        for i in range(a, a + 8):
            for j in range(b, b + 8):
                if (i + j) % 2 == 0:
                    if board[i][j] != "W":
                        idx1 += 1
                    if board[i][j] != "B":
                        idx2 += 1
                else:
                    if board[i][j] != "W":
                        idx2 += 1
                    if board[i][j] != "B":
                        idx1 += 1
        count.append(min(idx1, idx2))
print(min(count))
