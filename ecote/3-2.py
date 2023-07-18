n, m, k = map(int, input().split())
nums = list(map(int, input().split()))
nums.sort()
first = nums[-1]
second = nums[-2]

count = int(m / (k+1)) * k
count += m % (k+1)

result = 0
result += (count) * first
result += (m-count) * second
print(result)