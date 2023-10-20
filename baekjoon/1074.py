
# def z_search(n, r, c):
#     global current
#     if n > 2:
#         z_search(n/2, r, c)
#         z_search(n/2, r, c + n/2)
#         z_search(n/2, r + n/2, c)
#         z_search(n/2, r + n/2, c + n/2)
#     else:
#         for i in range(2):
#             for j in range(2):
#                 if r+i == R and c + j == C:
#                     print(current)
#                     return
#                 current += 1

# N, R, C = map(int, input().split())
# current = 0
# z_search(2**N, 0, 0)
# 위는 메모리 초과...
import sys
input = sys.stdin.readline
 
n, r, c = map(int, input().split())
idx = 0
while n != 0:
  n -= 1
  if r < 2**n and c < 2**n:
    idx += (2**n) * (2**n) * 0
  elif r < 2**n and c >= 2**n:
    idx += (2**n) * (2**n) * 1
    c -= 2**n
  elif r >= 2**n and c < 2**n:
    idx += (2**n) * (2**n) * 2
    r -= 2**n
  else:
    idx += (2**n) * (2**n) * 3
    r -= 2**n
    c -= 2**n
print(idx)