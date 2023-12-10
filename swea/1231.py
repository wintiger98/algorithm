def inorder(n):
    global ans

    if n <= N:
        inorder(n * 2)

        ans += val_arr[n]

        inorder(n * 2 + 1)


for test_case in range(1, 11):
    N = int(input())
    val_arr = [0 for _ in range(N + 1)]

    for _ in range(N):
        tmp = input().split()
        n, value, children = int(tmp[0]), tmp[1], tmp[2:]
        val_arr[n] = value

    ans = ""
    inorder(1)
    print(f"#{test_case} {ans}")
