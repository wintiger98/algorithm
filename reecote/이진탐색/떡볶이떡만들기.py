n, m = map(int, input().split())
array = list(map(int, input().split()))

start = 0
end = max(array)


def left_rice_cake(height):
    answer = 0
    for a in array:
        if a > height:
            answer += a - height
    return answer


result = 0
while start <= end:
    mid = (start + end) // 2

    if left_rice_cake(mid) < m:
        end = mid - 1
    else:
        result = mid
        start = mid + 1

print(result)
