import sys
input = sys.stdin.readline

prior = {'/':2,'*':2,'+':1,'-':1,'(':0}
exp = input().strip()
stack = []

for s in exp:
    if s.isalpha():
        print(s, end='')
    elif s == "(":
        stack.append(s)
    elif s == ")":
        while True:
            temp = stack.pop()
            if temp == "(":
                break
            print(temp, end = "")
    else:
        while stack and prior[stack[-1]] >= prior[s]:
            print(stack.pop(), end='')
        stack.append(s)

while stack:
    print(stack.pop(), end='')