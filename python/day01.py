freq = 0
for x in open("01.in"):
    freq += int(x)

print("Part 1: ", freq)
assert freq == 522

freq = 0
freqs = { freq }
stop = False
while (not stop):
    for x in open("01.in"):
        freq += int(x)
        if freq in freqs:
            stop = True
            break
        else:
            freqs.add(freq)

print("Part 2: ", freq)
assert freq == 73364
