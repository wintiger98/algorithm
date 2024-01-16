import sys

input = sys.stdin.readline

note = input().strip()
to_find = input().strip()

ans = 0
while True:
    cur_idx = note.find(to_find)
    if cur_idx == -1:
        break
    cur_idx += len(to_find)
    note = note[cur_idx:]
    ans += 1

print(ans)
