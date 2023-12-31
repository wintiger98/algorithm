# 회문 여부 메서드
def is_pal(arr, leng):
    for lst in arr:
        for i in range(0, 100 - leng + 1):
            # # 시간 초과
            # if lst[i : i + leng] == lst[i : i + leng][::-1]:
            #     return True
            for j in range(leng // 2):
                if lst[i + j] != lst[i + leng - 1 - j]:
                    break
            else:
                return True
    return False


for test_case in range(1, 11):
    _ = input()
    arr1 = [input() for _ in range(100)]
    arr2 = ["".join(x) for x in zip(*arr1)]

    for leng in range(100, 1, -1):
        if is_pal(arr1, leng) or is_pal(arr2, leng):
            break

    print(f"#{test_case} {leng}")
