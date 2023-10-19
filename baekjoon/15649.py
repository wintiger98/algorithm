import sys

input = sys.stdin.readline

n, m = map(int, input().split())

visited = [False for _ in range(n + 1)]
result = []


def backtracking():
    if len(result) == m:
        print(" ".join(map(str, result)))
        return
    for i in range(1, n + 1):
        if i not in result:
            print(i, "넣기")
            result.append(i)
            backtracking()
            print("빼기!!")
            result.pop()


backtracking()
