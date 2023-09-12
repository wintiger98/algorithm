def solution(bridge_length, weight, truck_weights):
    in_bridge = []  # 다리 위에 있는 트럭들(시간, 무게 쌍)
    answer = 0  # 총 시간
    target_idx = 0  # 제 차례가 온 idx
    complete = []
    while True:
        # 시간 계산
        for idx, truck in enumerate(in_bridge[:]):
            truck[0] += 1
            # 다 지났으면 퇴소
            if truck[0] == bridge_length:
                _, w = in_bridge.pop(idx)
                complete.append(w)
        answer += 1

        if complete == truck_weights:
            break
        if target_idx < len(truck_weights):
            in_bridge.append([0, truck_weights[target_idx]])
        # 길이 제한
        if len(in_bridge) > bridge_length:
            in_bridge.pop()
            continue
        # 무게 제한
        is_limit = False
        sum_weight = 0
        for truck in in_bridge:
            sum_weight += truck[1]
            if sum_weight > weight:
                is_limit = True
                in_bridge.pop()
                break
        if is_limit:
            continue
        else:
            target_idx += 1
    return answer


bridge_lengths = [2, 100, 100]
weights = [10, 100, 100]
truck_weightss = [[7, 4, 5, 6], [10], [10, 10, 10, 10, 10, 10, 10, 10, 10, 10]]

for i in range(3):
    print(solution(bridge_lengths[i], weights[i], truck_weightss[i]))
