def dfs(n):
    global ans
    if n == N:
        ans = max(ans, int("".join(map(str, lst))))
        return

    # L개에서 2개뽑는 모든 조합(둘을 교환)
    for i in range(L - 1):
        for j in range(i + 1, L):
            lst[i], lst[j] = lst[j], lst[i]

            chk = int("".join(map(str, lst)))
            if (n, chk) not in v:
                dfs(n + 1)
                v.append((n, chk))

            lst[i], lst[j] = lst[j], lst[i]


for i in range(1, int(input()) + 1):
    st, t = input().split()
    N = int(t)
    lst = []
    for ch in st:
        lst.append(int(ch))

    L = len(lst)
    ans = 0
    v = []

    dfs(0)
    print(f"#{i} {ans}")
