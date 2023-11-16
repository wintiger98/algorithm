for i in range(1, int(input()) + 1):
    n, k = map(int, input().split())
    scores = [list(map(int, input().split())) for _ in range(n)]

    total_scores = []

    for idx, score in enumerate(scores):
        total_scores.append(
            [idx + 1, score[0] * 0.35 + score[1] * 0.45 + score[2] * 0.2]
        )

    total_scores.sort(key=lambda x: x[1], reverse=True)

    rate = n // 10

    my_rank = 0
    for idx, s in enumerate(total_scores):
        if s[0] == k:
            my_rank = idx + 1
            break

    my_score = 0
    if my_rank % rate == 0:
        my_score = my_rank // rate
    else:
        my_score = my_rank // rate + 1
    score_mapping = {
        1: "A+",
        2: "A0",
        3: "A-",
        4: "B+",
        5: "B0",
        6: "B-",
        7: "C+",
        8: "C0",
        9: "C-",
        10: "D0",
    }
    print(f"#{i} {score_mapping[my_score]}")
