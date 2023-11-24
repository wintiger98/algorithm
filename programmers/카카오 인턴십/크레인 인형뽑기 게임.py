def solution(board, moves):
    answer = 0
    n = len(board)
    new_board = [[] for _ in range(n)]
    stack = []
    for i in range(n - 1, -1, -1):
        for j in range(n - 1, -1, -1):
            if board[i][j] > 0:
                new_board[j].append(board[i][j])

    for move in moves:
        if not new_board[move - 1]:
            continue
        tmp = new_board[move - 1].pop()

        if stack and tmp == stack[-1]:
            stack.pop()
            answer += 2
        else:
            stack.append(tmp)

    return answer
