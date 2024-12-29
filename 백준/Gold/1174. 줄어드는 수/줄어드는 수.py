# Main Logic
N = int(input())
if N > 1023:
    print(-1)
else:
    numList = list(range(10))
    result = list(range(10))
    for _ in range(10):
        if len(numList) < N:
            N -= len(numList)
        else:
            numList.sort()
            print(numList[N - 1])
            break
        numList = [10 * l + i for i in range(10) for l in numList if l % 10 > i]
