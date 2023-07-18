current = input()
row = int(current[1])
col = int(ord(current[0])) - int(ord('a')) + 1

dx = [2,2,-2,-2,1,1,-1,-1]
dy = [1,-1,1,-1,2,-2,2,-2]

count = 0
for i in range(len(dx)):
    tmp_row = row + dx[i]
    tmp_col = col + dy[i]
    if tmp_row < 1 or tmp_row > 8 or tmp_col < 1 or tmp_col > 8:
        continue
    else:
        count += 1
print(count)