while True:
    string = input()
    if string == ".":
        break
    stack = []
    is_yes = True
    for s in string:
        if s == "(" or s == "[":
            stack.append(s)
        elif s == ")":
            if not stack or stack[-1] == '[':
                is_yes = False
                break
            elif stack[-1] == "(":
                stack.pop()
        elif s == "]":
            if not stack or stack[-1] == '(':
                is_yes = False
                break
            elif stack[-1] == "[":
                stack.pop()
    if is_yes and not stack:
        print("yes")
    else:
        print("no")
            