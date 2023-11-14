n, k = map(int, input().split())
aa = list(map(int, input().split()))
bb = list(map(int, input().split()))
aa.sort()
bb.sort(reverse=True)

for i in range(k):
    aa[i], bb[i] = bb[i], aa[i]

print(sum(aa))
