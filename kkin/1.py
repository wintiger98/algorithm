def solution(friends, gifts):
    n = len(friends)
    give_table = [[0] * n for _ in range(n)]

    for gift in gifts:
        from_f, to_f = gift.split(" ")
        from_idx = friends.index(from_f)
        to_idx = friends.index(to_f)

        give_table[from_idx][to_idx] += 1

    present_nums = []
    for t in give_table:
        present_nums.append(sum(t))

    get_table = list(map(list, zip(*give_table)))
    for idx, t in enumerate(get_table):
        present_nums[idx] -= sum(t)
    answer = [0 for _ in range(n)]
    # 친구별로 받을 선물 취합
    for me in range(n):
        for friend in range(me + 1, n):
            give, ge = give_table[me][friend], get_table[me][friend]
            print(friends[me], friends[friend], give, ge)
            # 주고받았고, 우열이 있을 떄
            if give == ge or give == ge == 0:
                if present_nums[me] > present_nums[friend]:
                    answer[me] += 1
                elif present_nums[me] < present_nums[friend]:
                    answer[friend] += 1
            # 그 외
            else:
                if give > ge:
                    answer[me] += 1
                else:
                    answer[friend] += 1

    return max(answer)
