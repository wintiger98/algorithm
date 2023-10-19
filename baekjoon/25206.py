my_total_grade = 0
total_grade = 0

grade2num = {
    "A+": 4.5,
    "A0": 4.0,
    "B+": 3.5,
    "B0": 3.0,
    "C+": 2.5,
    "C0": 2.0,
    "D+": 1.5,
    "D0": 1.0,
    "F": 0.0,
}

for i in range(20):
    subject, subject_grade, grade = input().split()
    if grade == "P":
        pass
    else:
        my_total_grade += int(subject_grade[0]) * grade2num[grade]
        total_grade += int(subject_grade[0])
print(round(my_total_grade / total_grade, 5))
