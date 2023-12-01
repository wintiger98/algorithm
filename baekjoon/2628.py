X, Y = map(int, input().split())
cut_x = []
cut_y = []
for _ in range(int(input())):
    is_y, num = map(int, input().split())
    if is_y:
        cut_y.append(num)
    else:
        cut_x.append(num)

xs = []
ys = []


def get_lengths(arr, cuts, big):
    last = 0
    for cut in cuts:
        arr.append(cut - last)
        last = cut
    arr.append(big - last)
    return arr


cut_x.sort()
cut_y.sort()


xs = get_lengths(xs, cut_x, Y)
ys = get_lengths(ys, cut_y, X)

print(max(xs) * max(ys))
