import sys

MOD = 1_000_000_007

def multiply(M1, M2):
    mat = [[0] * M for _ in range(M)]
    for i in range(M):
        for j in range(M):
            for k in range(M):
                mat[i][j] += M1[i][k] * M2[k][j]
            mat[i][j] %= MOD
    return mat

N, M = map(int, sys.stdin.readline().split())
if N < M:
    print(1)
else:
    result = [[1 if i == j else 0 for i in range(M)] for j in range(M)]
    tmp = [[1 if i + 1 == j else 0 for i in range(M)] for j in range(M)]
    tmp[0][0] = tmp[0][M-1] = 1
    while N > 0:
        if N & 1:
            result = multiply(result, tmp)
        tmp = multiply(tmp, tmp)
        N >>= 1
    print(result[0][0])
