for test_case in range(1, int(input()) + 1):
    n, m = map(int, input().split())
    weights = list(map(int, input().split()))
    trucks = list(map(int, input().split()))

    weights.sort(reverse=True)
    trucks.sort(reverse=True)

    answer = 0

    weight_idx = 0
    truck_idx = 0
    while weight_idx < len(weights) and truck_idx < len(trucks):
        if weights[weight_idx] <= trucks[truck_idx]:
            answer += weights[weight_idx]
            weight_idx += 1
            truck_idx += 1
        else:
            weight_idx += 1

    print(f"#{test_case} {answer}")
