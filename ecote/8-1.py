# 재귀함수로 피보나치수열 구현
def fibo(x):
    if x == 1 or x == 2:
        return 1
    else:
        return fibo(x-1) + fibo(x-2)

number = int(input())
print(fibo(number))