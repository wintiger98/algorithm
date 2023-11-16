for i in range(1, int(input()) + 1):
    # w: 내가 사용하는 양
    p, q, r, s, w = map(int, input().split())
    # a사 : 1 리터당 p -> a : p * w
    # b사 : (r리터까지) Q 기본요금 + (r 리터 이상) 1리터당 S -> q if w <= r else q + (w-r) * s
    print(f"#{i}", end=" ")

    a = p * w
    b = q if w <= r else q + (w - r) * s

    print(min(a, b))
