def check(table, k):
    answer = 0
    # 1개수 세면서 넣을 수 있으면 통과 아니면 끝
    for row in table:
        cnt = 0

        for i in range(len(row)):
            if row[i] == 1:
                cnt += 1
            else:
                if cnt == k:
                    answer += 1
                cnt = 0
        if cnt == k:
            answer += 1

    return answer


for i in range(1, int(input()) + 1):
    n, k = map(int, input().split())
    puzzle = [list(map(int, input().split())) for _ in range(n)]

    print(f"#{i}", check(puzzle, k) + check(list(map(list, zip(*puzzle))), k))


t = int(input())


def check(table, k):
    result = 0
    for row in table:
        cnt = 0
        for i in range(len(row)):
            if row[i] == 1:
                cnt += 1
            else:
                if cnt == k:
                    result += 1
                cnt = 0
        if cnt == k:
            result += 1
    return result


for case_num in range(1, t + 1):
    n, k = map(int, input().split())
    puzzle = [list(map(int, input().split())) for _ in range(n)]
    # 행
    result = check(puzzle, k) + check(list(map(list, zip(*puzzle))), k)
    print(f"#{case_num} {result}")
