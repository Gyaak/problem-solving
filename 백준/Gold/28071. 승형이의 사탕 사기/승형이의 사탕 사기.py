import sys
from collections import deque

def set_input():
    N, M, K = map(int, sys.stdin.readline().split())
    candies = list(map(int, sys.stdin.readline().split()))
    candies.sort(reverse=True)
    return N, M, K, candies

def solve(*, N: int, M: int, K: int, candies: list):
    max_candies = candies[0] * M + 1
    visited = [M+1 for _ in range(max_candies+1)]
    q = deque()
    for candy in candies:
        q.append((1, candy))
        visited[candy] = 1

    while q:
        box, num = q.popleft()
        if box == M:
            continue
        for candy in candies:
            new_num = num + candy
            if visited[new_num] > M:
                visited[new_num] = box
                q.append((box+1, new_num))

    for i in range(max_candies - (max_candies % K), -1, -K):
        if visited[i] <= M:
            return i
    return 0

if __name__ == "__main__":
    N, M, K, candies = set_input()
    print(solve(N=N, M=M, K=K, candies=candies))
