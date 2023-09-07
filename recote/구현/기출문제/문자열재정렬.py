s = input()
string = []
sums = 0

for i in s:
    if i.isalpha():
        string.append(i)
    else:
        sums += int(i)
string.sort()
print(f"{''.join(string)}{sums}")
