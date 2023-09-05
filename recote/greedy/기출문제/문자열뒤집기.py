s = input()

# 1->0 횟수
zeros = 0
# 0->1 횟수
ones = 0

if s[0] == "1":
    zeros += 1
else:
    ones += 1

for i in range(len(s) - 1):
    if s[i] != s[i + 1]:
        if s[i + 1] == "1":
            zeros += 1
        else:
            ones += 1
print(min(zeros, ones))
