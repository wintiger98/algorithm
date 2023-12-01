for test_case in range(1, int(input()) + 1):
    answer = 0

    lst = list(map(int, input().split()))
    lst = lst[1:]

    for i in range(1, 20):
        for j in range(i):
            if lst[i] < lst[j]:
                answer += 1

    print(f"{test_case} {answer}")
