def solution(n, lost, reserve):
    mymy = set(lost) & set(reserve)

    lost = sorted(list(filter(lambda x: x not in mymy, lost)))
    reserve = sorted(list(filter(lambda x: x not in mymy, reserve)))

    # 빌리기
    for l in lost:
        if l - 1 in reserve:
            reserve.remove(l - 1)
        elif l + 1 in reserve:
            reserve.remove(l + 1)
        else:
            n -= 1

    return n
