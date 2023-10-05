n = list(map(int, input()))

nums = [0 for _ in range(10)]

for i in n:
    nums[i] += 1

sixnine = nums[6] + nums[9]
if sixnine % 2 == 0:
    nums[6] = sixnine // 2
    nums[9] = sixnine // 2
else:
    nums[6] = sixnine // 2
    nums[9] = sixnine // 2 + 1

print(max(nums))
