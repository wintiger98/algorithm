n = int(input())

board = [0 for _ in range(n)]
answer = 0


def check(x):
    for i in range(x):
        if board[x] == board[i] or (x - i == abs(board[x] - board[i])):
            return False
    return True


def n_queens(x):
    global answer
    if x == n:
        answer += 1
        return
    else:
        for i in range(n):
            board[x] = i
            if check(x):
                n_queens(x + 1)


n_queens(0)
print(answer)
