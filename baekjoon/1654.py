k, n = map(int, input().split())
lansuns = []
for _ in range(k):
    lansuns.append(int(input()))

start = 1
end = max(lansuns)

while start <= end:
    mid = (start + end) // 2
    lines = 0
    for i in lansuns:
        lines += i // mid

    if lines >= n:
        start = mid + 1
    else:
        end = mid - 1

print(end)
