t = int(input())

for i in range(1, t+1):
    a, b = map(int, input().split())
    print(f"#{i}", end=" ")
    if a < b:
        print("<")
    elif a == b:
        print("=")
    else:
        print(">")