from collections import defaultdict
import array

def parse(line): # [1518-06-15 00:15] falls asleep
    t = line[1:17]
    o = line[19:]
    if "Guard" in o:
        o = o.split()[1][1:]

    return t, o

map = defaultdict()

for line in sorted(open("04.in")):
    t, o = parse(line.strip("\n"))
    if "falls asleep" == o:
        start_sleep = int(t[14:16])
    elif "wakes up" == o:
        stop_sleep = int(t[14:16])
        for x in range(start_sleep, stop_sleep):
            map[guard][x] += 1
    else:
        guard = int(o)
        if not guard in map:
            map[guard] = array.array('B', [0] * 60)

sum_max = -1
for x in map:
    if sum(map[x]) > sum_max:
        guard = x
        sum_max = sum(map[guard])

minute = map[guard].index(max(map[guard]))

print("Part 1: ", guard, "*", minute, "=", guard * minute)
assert 106710 == guard * minute

max_minutes = -1
for x in map:
    if max(map[x]) > max_minutes:
        max_minutes = max(map[x])
        guard = x

minute = map[guard].index(max(map[guard]))

print("Part 2: ", guard, "*", minute, "=", guard * minute)
assert 10491 == guard * minute
