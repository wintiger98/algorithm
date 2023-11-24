from collections import deque


def solution(people, limit):
    people = deque(sorted(people))
    answer = 0

    while people:
        right = people.pop()
        if len(people) != 0 and people[0] + right <= limit:
            people.popleft()
        answer += 1

    return answer
