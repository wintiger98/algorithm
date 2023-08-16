class MyOperation:
    def __init__(self, arrays, n, m):
        self.arrays = arrays
        self.n = n
        self.m = m

    # 상하 반전
    def one(self):
        for i in range(self.n // 2):
            self.arrays[i], self.arrays[self.n - i - 1] = (
                self.arrays[self.n - i - 1],
                self.arrays[i],
            )

    # 좌우 반전
    def two(self):
        for array in self.arrays:
            for i in range(self.m // 2):
                array[i], array[self.m - i - 1] = array[self.m - i - 1], array[i]

    # 오른쪽 90도 회전
    def three(self):
        tmp_array = [[0] * self.n for _ in range(self.m)]

        for i in range(self.n):
            for j in range(self.m):
                tmp_array[j][i] = self.arrays[self.n - 1 - i][j]
        self.n, self.m = self.m, self.n
        self.arrays = tmp_array

    # 왼쪽 90도 회전
    def four(self):
        tmp_array = [[0] * self.n for _ in range(self.m)]

        for i in range(self.n):
            for j in range(self.m):
                tmp_array[j][i] = self.arrays[i][self.m - 1 - j]
        self.n, self.m = self.m, self.n
        self.arrays = tmp_array

    # 시계방향으로 돌리기
    def five(self):
        tmp_array = [[0] * self.m for _ in range(self.n)]
        for i in range(n // 2):  # move position: 1 -> 2
            for j in range(m // 2):
                tmp_array[i][j + m // 2] = self.arrays[i][j]

        for i in range(n // 2):  # move position: 2 -> 3
            for j in range(m // 2, m):
                tmp_array[i + n // 2][j] = self.arrays[i][j]

        for i in range(n // 2, n):  # move position: 3 -> 4
            for j in range(m // 2, m):
                tmp_array[i][j - m // 2] = self.arrays[i][j]

        for i in range(n // 2, n):  # move position: 4 -> 1
            for j in range(m // 2):
                tmp_array[i - n // 2][j] = self.arrays[i][j]

        self.arrays = tmp_array

    # 반시계방향으로 돌리기
    def six(self):
        tmp_array = [[0] * self.m for _ in range(self.n)]
        for i in range(n // 2):  # move position: 1 -> 4
            for j in range(m // 2):
                tmp_array[i + n // 2][j] = self.arrays[i][j]

        for i in range(n // 2, n):  # move position: 4 -> 3
            for j in range(m // 2):
                tmp_array[i][j + m // 2] = self.arrays[i][j]

        for i in range(n // 2, n):  # move position: 3 -> 2
            for j in range(m // 2, m):
                tmp_array[i - n // 2][j] = self.arrays[i][j]

        for i in range(n // 2):  # move position: 2 -> 1
            for j in range(m // 2, m):
                tmp_array[i][j - m // 2] = self.arrays[i][j]

        self.arrays = tmp_array


n, m, r = map(int, input().split())
arrays = [list(map(int, input().split())) for _ in range(n)]
todos = list(map(int, input().split()))

my = MyOperation(arrays, n, m)

for todo in todos:
    if todo == 1:
        arr = my.one()
    elif todo == 2:
        arr = my.two()
    elif todo == 3:
        arr = my.three()
        n, m = m, n
    elif todo == 4:
        arr = my.four()
        n, m = m, n
    elif todo == 5:
        arr = my.five()
    else:
        arr = my.six()

for array in my.arrays:
    print(*array)
