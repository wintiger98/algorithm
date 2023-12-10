# 후위 연산
def postorder(n):
    if lst[n]:
        if lst[n] == "+":
            return postorder(ch1[n]) + postorder(ch2[n])
        elif lst[n] == "-":
            return postorder(ch1[n]) - postorder(ch2[n])
        elif lst[n] == "*":
            return postorder(ch1[n]) * postorder(ch2[n])
        elif lst[n] == "/":
            return postorder(ch1[n]) / postorder(ch2[n])
        else:
            return int(lst[n])
    else:
        return 0


for test_case in range(1, 11):
    N = int(input())

    lst = [0] * (N + 1)
    ch1 = [0] * (N + 1)
    ch2 = [0] * (N + 1)

    # [1] 트리에 저장
    for _ in range(N):
        tmp = input().split()
        idx = int(tmp[0])
        lst[idx] = tmp[1]
        if len(tmp) > 2:
            ch1[idx] = int(tmp[2])
            ch2[idx] = int(tmp[3])

    ans = postorder(1)

    print(f"#{test_case} {int(ans)}")
