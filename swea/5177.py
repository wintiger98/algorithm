# 이진 최소힙
for test_case in range(1, int(input()) + 1):
    N = int(input())
    nums = [0] + list(map(int, input().split()))
    h = [0 for _ in range(N + 1)]
    last = 0
    for i in range(1, N + 1):
        last += 1
        h[last] = nums[i]

        c = last
        while c // 2 and h[c] < h[c // 2]:
            h[c], h[c // 2] = h[c // 2], h[c]
            c //= 2

    answer = 0
    while last:
        last //= 2
        answer += h[last]
    print(f"#{test_case} {answer}")
