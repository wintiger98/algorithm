import sys
input = sys.stdin.readline

t = int(input())
for _ in range(t):
    string = input().strip()
    stack = []
    is_no = False
    for s in string:
        if s == "(":
            stack.append(s)
        else:
            if not stack:
                is_no = True
                break
            else:
                stack.pop()
    if is_no or stack:
        print("NO")
    else:
        print("YES")