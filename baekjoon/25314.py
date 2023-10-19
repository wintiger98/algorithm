n = int(input())
result = ""

if n % 4 == 0:
    result += "long " * (n // 4)
else:
    result += "long " * (n // 4 + 1)

result += "int"

print(result)
