# N = 1
# lst = []


def inorder(n):
    global cnt
    if n <= N:
        inorder(n * 2)

        lst[n] = cnt
        cnt += 1

        inorder(n * 2 + 1)
