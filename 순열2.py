def permutations(array):
    # 리스트가 비어있으면, 빈 리스트 반환
    if len(array) == 0:
        return [[]]

    # 리스트 첫 번째 요소를 제외한 나머지 요소들에 대해 순열 구하기

    # 첫 번째 요소를 포함하는 순열과 포함하지 안흔 순열 구하기
    result = []
    for p in permutations(array[1:]):
        result.append(p)
        result.append([array[0]] + p)

    return result


print(permutations([1, 2, 3, 4]))
