students = []
for _ in range(int(input())):
    students.append(input().split())

students.sort(key=lambda x: x[1])

for student in students:
    print(student[0], end=" ")
