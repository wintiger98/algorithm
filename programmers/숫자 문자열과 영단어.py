def solution(s):
    answer = ""
    word = ""
    word2num = {
        "zero": 0,
        "one": 1,
        "two": 2,
        "three": 3,
        "four": 4,
        "five": 5,
        "six": 6,
        "seven": 7,
        "eight": 8,
        "nine": 9,
    }
    words = word2num.keys()
    for i in s:
        if i.isnumeric():
            answer += str(i)
        else:
            word += i
            if word in words:
                answer += str(word2num[word])
                word = ""

    answer = int(answer)
    return answer
