nums = []
for _ in range(int(input())):
    nums.append(int(input()))
nums.sort(reverse=True)
print(*nums)
