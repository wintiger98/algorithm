def solution(d, budget):
    d.sort()

    answer = 0
    while True:
        if answer >= len(d):
            break
        budget -= d[answer]
        if budget < 0:
            break
        elif budget == 0:
            answer += 1
            break
        answer += 1

    return answer
