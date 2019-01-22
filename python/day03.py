import numpy as np

def parse(line):
    split = line.split() # #1 @ 935,649: 22x22
    id = split[0][1:]
    x, y = split[2].split(",")
    w, h = split[3].split("x")

    return int(id), int(x), int(y[:-1]), int(w), int(h)

matrix = np.zeros((1100, 1100), np.int8)
for line in open("03.in"):
    id, x, y, w, h = parse(line)

    for dx in range(w):
        for dy in range(h):
            matrix[y + dy][x + dx] += 1

sum = 0
for x in range(1100):
    for y in range(1100):
        if matrix[y][x] > 1:
            sum += 1

print("Part 1: ", sum)
assert 111326 == sum

for line in open("03.in"):
    id, x, y, w, h = parse(line)

    found = True
    for dx in range(w):
        for dy in range(h):
            if matrix[y + dy][x + dx] != 1:
                found = False
                break
        if not found:
            break

    if found:
        break

print("Part 2: ", id)
assert 1019 == id
