word = input().strip()

result = ""
for w in word:
    if w in "ABC":
        result += "2"
    elif w in "DEF":
        result += "3"
    elif w in "GHI":
        result += "4"
    elif w in "JKL":
        result += "5"
    elif w in "MNO":
        result += "6"
    elif w in "PQRS":
        result += "7"
    elif w in "TUV":
        result += "8"
    elif w in "WXYZ":
        result += "9"
result_time = 0
for r in result:
    if int(r) > 1:
        result_time += 2 + int(r) - 1
    else:
        result_time += 2
print(result_time)
