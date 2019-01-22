s = open("05.in").read().strip()

def peek(stack):
    return stack[-1]

def solve(txt):
    stack = []
    for ch in txt:
        if len(stack) == 0 or ch != peek(stack).swapcase():
            stack.append(ch)
        else:
            stack.pop()
    return len(stack)

l = solve(s)

print("Part 1: ", l)
assert 10180 == l

l = min(solve(s.replace(c, '').replace(c.upper(), '')) for c in set(s.lower()))

print("Part 2: ", l)
assert 5668 == l
