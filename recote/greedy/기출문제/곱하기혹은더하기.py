nums = list(map(int, input()))

result = nums[0]
for num in nums[1:]:
    if num <= 1 or result <= 1:
        result += num
    else:
        result *= num

print(result)
