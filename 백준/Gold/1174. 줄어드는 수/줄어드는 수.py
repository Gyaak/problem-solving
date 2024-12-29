# Main Logic
N = int(input())
numList = list(range(10))
result = list(range(10))
for _ in range(9):
    numList = [10 * l + i for i in range(10) for l in numList if l % 10 > i]
    result += numList
result.sort()
print(-1 if len(result) < N else result[N - 1])
