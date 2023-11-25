def solution(numbers, hand):
    answer = ""

    left_area = [1, 4, 7]
    right_area = [3, 6, 9]

    left = (3, 0)
    right = (3, 2)

    number2xy = {i: ((i - 1) // 3, (i - 1) % 3) for i in range(1, 10)}
    number2xy[0] = (3, 1)

    for number in numbers:
        if number in left_area:
            left = number2xy[number]
            answer += "L"
        elif number in right_area:
            right = number2xy[number]
            answer += "R"
        else:
            target = number2xy[number]
            d_left = abs(target[0] - left[0]) + abs(target[1] - left[1])
            d_right = abs(target[0] - right[0]) + abs(target[1] - right[1])

            if d_left < d_right:
                left = target
                answer += "L"
            elif d_left > d_right:
                right = target
                answer += "R"
            else:
                if hand == "left":
                    left = target
                    answer += "L"
                else:
                    right = target
                    answer += "R"
    return answer


numbers = [1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5]
hand = "right"

print(solution(numbers, hand))
LRLRLLLLLRR
LRLLLRLLRRL
