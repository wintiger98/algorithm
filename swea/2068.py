t = int(input())
for i in range(1, 1+t):
    nums = list(map(int, input().split()))

    print(f"#{i} {max(nums)}")