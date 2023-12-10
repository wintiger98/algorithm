def solve():
    for i in range(len(lst2)):
        lst2[i] = (lst2[i] + 1) % 2
        # [1] 2 -> 10
        num = 0
        for n in lst2:
            num = num * 2 + n

        # [2] 10 -> 3
        conv3, t = [], num

        while t > 0:
            conv3.insert(0, t % 3)
            t //= 3

        # 길이가 다른 경우 고려해서 마지막부터(뒤집어서) 비교
        # cnt: 다른 횟수
        r1, r2, cnt = conv3[::-1], lst3[::-1], 0
        # 더 짧은 길이 만큼만
        for j in range(min(len(r1), len(r2))):
            if r1[j] != r2[j]:
                cnt += 1
        cnt += abs(len(r1) - len(r2))
        if cnt == 1:
            return num

        lst2[i] = (lst2[i] + 1) % 2


for test_case in range(1, int(input()) + 1):
    lst2 = list(map(int, input()))
    lst3 = list(map(int, input()))

    ans = solve()
    print(f"#{test_case} {ans}")
