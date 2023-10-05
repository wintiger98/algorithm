def solution(s):
    answer = []
    count = 0
    zero_count = 0

    while s != "1":
        zero_count += s.count("0")

        s = "1" * s.count("1")

        s = str(bin(len(s)))[2:]

        count += 1
    answer = [count, zero_count]
    return answer
