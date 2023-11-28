from collections import deque
from itertools import combinations


def check_submit(cards, now, target, used):
    result = []
    new_cards = []

    # 사용 전 카드만 모으기
    for i in range(now):
        if used[i] == False:
            new_cards.append(i)

    for c in combinations(new_cards, 2):
        if cards[c[0]] + cards[c[1]] == target:
            result.append(c)
    # 합이 n+1이 되는 인덱스 리스트 반환
    return result


def cards_check(cards, now, get_count, coin, used, target):
    # 코인 업데이트
    coin -= get_count

    # 제출 가능 경우의수
    result = check_submit(cards, now, target, used)
    if not result:
        return [], 0

    tmp_useds = []
    for idx1, idx2 in result:
        idx1, idx2 = min(idx1, idx2), max(idx1, idx2)
        tmp_used = used.copy()
        tmp_used[idx1] = True
        tmp_used[idx2] = True
        tmp_useds.append(tmp_used)
    # used, coin 반환
    return tmp_useds, coin


def solution(coin, cards):
    answer = 0

    n = len(cards)
    target = n + 1
    queue = deque()
    # 현재 위치, 라운드, 사용 여부
    queue.append((n // 3, 0, coin, [False] * n))
    while queue:
        now, n_round, n_coin, used = queue.popleft()

        if now >= n:
            answer = max(answer, n_round)
            continue
        if n_coin < 0:
            answer = max(answer, n_round - 1)
            continue
        # 현재 위치 업데이트
        now += 2

        # 다 가져가는 경우
        new_useds, new_coin = cards_check(
            now=now, get_count=2, coin=n_coin, used=used, target=target, cards=cards
        )
        for u in new_useds:
            queue.append((now, n_round + 1, new_coin, u))

        # 다 안 가져가는 경우
        tmp_used = used.copy()
        tmp_used[now - 2] = True
        tmp_used[now - 1] = True
        new_useds, new_coin = cards_check(
            now=now, get_count=0, coin=n_coin, used=tmp_used, target=target, cards=cards
        )
        for u in new_useds:
            queue.append((now, n_round + 1, new_coin, u))

        # 하나만 가져가는 경우
        # 앞에가져가는 경우
        tmp_used = used.copy()
        tmp_used[now - 2] = True
        new_useds, new_coin = cards_check(
            now=now, get_count=0, coin=n_coin, used=tmp_used, target=target, cards=cards
        )
        for u in new_useds:
            queue.append((now, n_round + 1, new_coin, u))
        # 뒤 가져가는경우
        tmp_used = used.copy()
        tmp_used[now - 1] = True
        new_useds, new_coin = cards_check(
            now=now, get_count=0, coin=n_coin, used=tmp_used, target=target, cards=cards
        )
        for u in new_useds:
            queue.append((now, n_round + 1, new_coin, u))
    return answer


input1 = 4


input2 = [3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4]


print(solution(input1, input2))
