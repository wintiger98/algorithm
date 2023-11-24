def check(b, u):
    if len(b) != len(u):
        return False
    for tb, tu in zip(b, u):
        if tb == "*":
            if not tu:
                return False
        else:
            if tb != tu:
                return False
    return True


def solution(user_id, banned_id):
    arr = [[] for _ in range(len(banned_id))]

    for i, b in enumerate(banned_id):
        for u in user_id:
            if check(b, u):
                arr[i].append(u)

    answer = set()

    def dfs(lst, idx):
        if len(lst) != len(set(lst)):
            return
        if idx == len(arr):
            # 제재 아이디 목록의 생성 순서에 따라 다른 것으로 취급될 수 있으니 sorted!
            answer.add(tuple(sorted(set(lst))))
        elif idx < len(arr):
            for a in arr[idx]:
                dfs(lst + [a], idx + 1)

    dfs([], 0)
    print(answer)
    return len(answer)
