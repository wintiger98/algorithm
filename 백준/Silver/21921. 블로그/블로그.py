import sys
input = sys.stdin.readline

def solution():
    N, X = map(int, input().split())
    blogs = list(map(int, input().split()))

    # X일 동안 가장 많이 들어온 방문자 수 구하기
    # 가장 많이 들어온 방문자 수 출력(0이면 SAD)
    # 0이 아닌경우 기간이 몇 개 있는지 출력
    if sum(blogs) == 0:
        print("SAD")
        return

    sums = [0]*(N+1)
    for i in range(1, N+1):
        sums[i] = sums[i-1] + blogs[i-1]
    
    max_visit = 0
    max_cnt = 0
    for i in range(N+1-X):
        tmp_sum = sums[i+X] - sums[i]
        if tmp_sum > max_visit:
            max_visit = tmp_sum
            max_cnt = 1
        elif tmp_sum == max_visit:
            max_cnt += 1
    print(max_visit)
    print(max_cnt)

solution()