n, m = map(int, input().split())

balls = [i for i in range(n + 1)]

for i in range(m):
    from_ball, to_ball = map(int, input().split())
    balls[from_ball], balls[to_ball] = balls[to_ball], balls[from_ball]

print(*balls[1:])
