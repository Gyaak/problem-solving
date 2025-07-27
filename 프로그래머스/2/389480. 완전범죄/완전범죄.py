def solution(info, n, m):
    num = len(info)
    dp = [
        [
            [False for _ in range(m)]
            for _ in range(n)
        ]
        for _ in range(num)
    ]
    
    if info[0][0] < n:
        dp[0][info[0][0]][0] = True
    if info[0][1] < m:
        dp[0][0][info[0][1]] = True
        
    for i in range(1,num):
        for j in range(n):
            for k in range(m):
                n_val = False
                m_val = False
                if j >= info[i][0]:
                    n_val = dp[i-1][j-info[i][0]][k]
                if k >= info[i][1]:
                    m_val = dp[i-1][j][k-info[i][1]]
                dp[i][j][k] = n_val or m_val
    answer = -1
    for i in range(n):
        for j in range(m):
            if dp[num-1][i][j]:
                return i
    return answer