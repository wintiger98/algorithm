for i in range(1, int(input()) + 1):
    word = input()

    print(f"#{i}", end=" ")

    if word == word[::-1]:
        print(1)
    else:
        print(0)
