def inorder(n):
    global cnt
    if n <= N:
        inorder(n * 2)
        lst[n] = cnt
        cnt += 1
        inorder(n * 2 + 1)


for test_case in range(1, int(input()) + 1):
    N = int(input())

    heap = []

    lst = [0] * (N + 1)

    # 완전 이진트리 기준 순회(inorder)
    cnt = 1

    inorder(1)

    print(f"#{test_case} {lst[1]} {lst[N//2]}")
