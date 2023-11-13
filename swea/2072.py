import sys
input = sys.stdin.readline

t = int(input())

for i in range(1, t+1):
    nums = list(map(int, input().split()))
    
    answer = 0
    for num in nums:
        if num%2 == 1:
            answer += num
    
    print(f"#{i} {answer}")