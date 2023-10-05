x = int(input())

depth = 0
depth_sum = 0
while x > depth_sum:
    depth += 1
    depth_sum += depth

depth_num = x - (depth_sum - depth)

if depth % 2 == 0:
    # 위에서 시작
    left, right = 1, depth
    for i in range(depth_num - 1):
        left += 1
        right -= 1
else:
    # 아래서 시작
    left, right = depth, 1
    for i in range(depth_num - 1):
        left -= 1
        right += 1

print(f"{left}/{right}")
