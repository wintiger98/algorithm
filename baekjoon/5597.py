students = [i for i in range(1, 31)]

for _ in range(28):
    tmp = int(input())
    students.remove(tmp)

for student in students:
    print(student)
