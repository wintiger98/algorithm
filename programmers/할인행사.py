import copy


def solution(want, number, discount):
    answer = 0
    want_number = {want[i]: number[i] for i in range(len(want))}

    for i in range(len(discount) - 9):
        tmp_discount = copy.deepcopy(want_number)
        for j in range(i, i + 10):
            if tmp_discount.get(discount[j]) and tmp_discount[discount[j]] > 0:
                tmp_discount[discount[j]] -= 1
        if sum(tmp_discount.values()) == 0:
            answer += 1

    return answer
