def solution(n, times):
    answer = 0
    # right: 가장 비효율적으로 심사했을 때 시간
    # 가장 긴 심사관에게 모두 심사받으러 가는 경우
    left, right = 1, max(times) * n

    while left <= right:
        mid = (left + right) // 2
        people = 0
        for time in times:
            people += mid // time
            if people >= n:
                break

        if people >= n:
            answer = mid
            right = mid - 1
        else:
            left = mid + 1

    return answer
