from itertools import combinations, product


def get_sums(dices):
    result = []
    for idxs in product([i for i in range(6)], repeat=len(dices)):
        tmp_sum = 0
        for i in range(len(idxs)):
            tmp_sum += dices[i][idxs[i]]
        result.append(tmp_sum)
    return result


def solution(dice):
    answer = []
    n = len(dice)
    dice_idx = [i for i in range(1, n + 1)]
    a_cases = list(combinations(dice_idx, n // 2))
    b_cases = []
    for a_case in a_cases:
        tmp_case = [i for i in dice_idx if i not in a_case]
        b_cases.append(tmp_case)
    # idx 편의
    dice = [0] + dice
    total_wins = []

    for a_case, b_case in zip(a_cases, b_cases):
        a_dices = []
        for a_c in a_case:
            a_dices.append(dice[a_c])
        a_sums = get_sums(a_dices)
        b_dices = []
        for b_c in b_case:
            b_dices.append(dice[b_c])
        b_sums = get_sums(b_dices)
        tmp_wins = 0
        for a_sum in a_sums:
            for b_sum in b_sums:
                if a_sum > b_sum:
                    tmp_wins += 1
        total_wins.append(tmp_wins)
    max_total_wins = max(total_wins)
    for idx, value in enumerate(total_wins):
        if value == max_total_wins:
            return sorted(list(a_cases[idx]))
    return 1
