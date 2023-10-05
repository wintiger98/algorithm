def solution(s):
    left = 0
    for i in s:
        if i == "(":
            left += 1
        else:
            if left > 0:
                left -= 1
            else:
                return False
    if left == 0:
        return True
    else:
        return False
