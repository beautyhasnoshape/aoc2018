count2 = 0
count3 = 0

for s in open("02.in"):
    found2 = False
    found3 = False
    for c in s:
        if not found2 and s.count(c) == 2:
            found2 = True
            count2 += 1
        elif not found3 and s.count(c) == 3:
            found3 = True
            count3 += 1

print("Part 1: ", count2 * count3)
assert 5434 == count2 * count3

lines = list()
for s in open("02.in"):
    lines.append(s.strip("\n"))

for i in range(0, len(lines) - 1):
    for j in range(i + 1, len(lines)):
        count = 0
        for k in range(0, len(s)):
            if lines[i][k] == lines[j][k]:
                count += 1
        if (count == len(s) - 1):
            result = str()
            for k in range(0, len(s)):
                if lines[i][k] == lines[j][k]:
                    result += lines[i][k]

print("Part 2: ", result)   
assert "agimdjvlhedpsyoqfzuknpjwt" == result         
