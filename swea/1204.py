for _ in range(1, int(input()) + 1):
    n = int(input())

    nums = list(map(int, input().split()))

    scores = [0 for i in range(101)]

    for num in nums:
        scores[num] += 1

    many_score = max(scores)

    for i in range(100, -1, -1):
        if scores[i] == many_score:
            print(f"#{n} {i}")
            break
