def solution(s, n):
    answer = ""
    for i in s:
        tmp = ord(i) + n
        # 공백
        if i == " ":
            answer += i
        # 대문자
        elif ord(i) >= ord("A") and ord(i) <= ord("Z"):
            if ord(i) + n <= ord("Z"):
                tmp = ord(i) + n
            else:
                tmp = ord("A") + ord(i) + n - ord("Z") - 1
            answer += chr(tmp)
        # 소문자
        else:
            if ord(i) + n <= ord("z"):
                tmp = ord(i) + n
            else:
                tmp = ord("a") + ord(i) + n - ord("z") - 1
            answer += chr(tmp)
    return answer
