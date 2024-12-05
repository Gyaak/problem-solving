import queue

def getPrimeSet(N):
    isPrime = [False, False] + [True] * (N-1)
    primeSet = set()
    for i in range(2, N+1):
        if isPrime[i]:
            primeSet.add(i)
            for j in range(i + i, N + 1, i):
                isPrime[j] = False
    return primeSet

def isBetween(target, A, B):
    if A <= target <= B:
        return True
    return False

def hasPrime(A, B, primeSet):
    for i in range(A, B + 1):
        if primeSet.__contains__(i):
            return True
    return False

def getFingerSnaps():
    return [
        lambda x: x//2,
        lambda x: x//3,
        lambda x: x+1,
        lambda x: x-1
    ]

def solve(N, A, B, primeSet):
    q = queue.Queue()
    maxVal = max(N, B)
    visited = [False] * (maxVal + 1)
    visited[N] = True
    q.put([N, 0])
    while not q.empty():
        [cur, num] = q.get()
        if primeSet.__contains__(cur) and isBetween(cur, A, B):
            return num
        for fingerSnap in getFingerSnaps():
            result = fingerSnap(cur)
            if result < 0 or result > maxVal:
                continue
            if not visited[result] and 0 <= result <= maxVal:
                q.put([result, num + 1])
                visited[result] = True
    return -1

# Main Logic
T = int(input())
primeSet = getPrimeSet(100_001)
for t in range(T):
    N, A, B = map(int, input().split())
    print(solve(N, A, B, primeSet) if hasPrime(A, B, primeSet) else -1)