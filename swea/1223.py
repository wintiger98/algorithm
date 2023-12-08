# 우선순위
pri = {"*": 2, "+": 1}

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
        # 연산자
        else:
            # 연산자 없으면 추가
            if not stk:
                stk.append(ch)
            else:
                # 만약 연산자 제일 위에보다 우선순위가 높다면 추가
                if pri[ch] > pri[stk[-1]]:
                    stk.append(ch)
                else:
                    # 우선순위가 같아질때까지 모조리 equ에 추가 후 연산자 추가
                    while stk and pri[ch] <= pri[stk[-1]]:
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
