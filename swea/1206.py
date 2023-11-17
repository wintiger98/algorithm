for a in range(1, 11):
    n = int(input())
    buildings = list(map(int, input().split()))
    answer = 0
    for i in range(2, n - 2):
        around = buildings[i - 2 : i + 3]
        max_height = max(around)
        if buildings[i] == max_height:
            answer += max_height - max(around[:2] + around[3:])
    print(f"#{a} {answer}")
