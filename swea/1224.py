# 들어올 때 우선순위
icp = {"(": 3, "*": 2, "+": 1}
# 스택에서 우선순위
isp = {"*": 2, "+": 1, "(": 0}

for test_case in range(1, 11):
    N = int(input())
    st = input()

    # 후위 표기식
    equ = ""
    # 연산자
    stk = []
    # [1] 중위 -> 후위
    for ch in st:
        # 숫자
        if ch.isdigit():
            equ += ch
        else:
            if ch == ")":
                # 괄호 만날 때까지 pop하기
                while stk:
                    t = stk.pop()
                    if t == "(":
                        break
                    else:
                        equ += t
            # 연산자
            else:
                # 만약 연산자 제일 위에보다 우선순위가 높다면
                while stk and icp[ch] <= isp[stk[-1]]:
                    equ += stk.pop()
                stk.append(ch)
    # 남은 연산자 처리
    while stk:
        equ += stk.pop()

    # [2] 후위 표기식 연산
    for ch in equ:
        if ch.isdigit():
            # 스택 비어있으니 숫자 모은 스택으로 재사용
            stk.append(int(ch))
        else:
            op2 = stk.pop()
            op1 = stk.pop()
            if ch == "*":
                stk.append(op1 * op2)
            else:
                stk.append(op1 + op2)
    print(f"#{test_case} {stk[0]}")
