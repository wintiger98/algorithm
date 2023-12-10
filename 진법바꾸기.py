# 10 -> n
def func(n, q):
    rev_base = ""
    while n > 0:
        n, mod = divmod(n, q)
        rev_base += str(mod)
    return rev_base[::-1]


# n -> 10
# int(string, base)
int("101", 2)
