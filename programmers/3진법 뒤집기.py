import string

tmp = string.digits + string.ascii_lowercase


def convert(num, base):
    q, r = divmod(num, base)
    if q == 0:
        return tmp[r]
    else:
        return convert(q, base) + tmp[r]


def solution(n):
    three = convert(n, 3)

    answer = int(three[::-1], 3)
    return answer
