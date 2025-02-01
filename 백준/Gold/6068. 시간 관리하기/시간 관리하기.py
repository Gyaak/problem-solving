import sys

def setInput() -> list:
    N = int(input())
    return [tuple(map(int, sys.stdin.readline().split())) for _ in range(N)]

def solve(inputs: list) -> int:
    works = sorted(inputs, key=lambda x: -x[1])
    res_time = works[0][1]
    for work in works:
        res_time = min(res_time, work[1]) - work[0]
        if res_time < 0:
            return -1
    return res_time

if __name__ == "__main__":
    print(solve(setInput()))