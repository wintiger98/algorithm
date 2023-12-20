dct1 = {
    "0": "0000",
    "1": "0001",
    "2": "0010",
    "3": "0011",
    "4": "0100",
    "5": "0101",
    "6": "0110",
    "7": "0111",
    "8": "1000",
    "9": "1001",
    "A": "1010",
    "B": "1011",
    "C": "1100",
    "D": "1101",
    "E": "1110",
    "F": "1111",
}
dct2 = {
    "211": 0,
    "221": 1,
    "122": 2,
    "411": 3,
    "132": 4,
    "231": 5,
    "114": 6,
    "312": 7,
    "213": 8,
    "112": 9,
}


def solve():
    ans = set()
    for st in sset:
        bst = ""  # [1] 16 -> 2
        for ch in st:
            bst += dct1[ch]

        old, cnts = 0, []  # [1] 연속개수를 카운트
        for i in range(len(bst)):
            if bst[i] != bst[old]:
                cnts.append(i - old)
                old = i
        # print(cnts)
        # [3] 가장 얇은 두께로 나머지 숫자를 나눠줌
        pwd = []
        for i in range(1, len(cnts), 4):
            mn = min(cnts[i : i + 3])
            key = str(cnts[i] // mn) + str(cnts[i + 1] // mn) + str(cnts[i + 2] // mn)
            pwd.append(dct2[key])

        for i in range(0, len(pwd), 8):
            ans.add(tuple(pwd[i : i + 8]))

    # 정답처리: 정상이면 누적
    sm = 0
    for code in ans:
        if (sum(code[0:8:2]) * 3 + sum(code[1:8:2])) % 10 == 0:
            sm += sum(code)
    return sm


T = int(input())
for test_case in range(1, T + 1):
    N, M = map(int, input().strip().split())

    # 입력도 중복제거해서 받기
    sset = set()
    for _ in range(N):
        st = input().strip()
        if st.count("0") != len(st):
            sset.add(st)

    ans = solve()
    print(f"#{test_case} {ans}")
