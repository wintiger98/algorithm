n, m, k = map(int, input().split())
arr = list(map(int, input().split()))
answer = 0
arr.sort(reverse=True)

first = arr[0]
second = arr[1]

# 반복되는 수열의 크기는 k+1이기때문에!
count = (m//(k+1)) * k
count += m % (k+1)

answer += (count) * first
answer += (m-count) * second

print(answer)