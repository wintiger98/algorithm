for t in range(1, 11):
    dump = int(input())
    heights = list(map(int, input().split()))

    for i in range(dump):
        heights[heights.index(max(heights))] -= 1
        heights[heights.index(min(heights))] += 1

    print(f"#{t} {max(heights)-min(heights)}")
