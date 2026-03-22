from collections import deque
from math import floor


def solution(players, m, k):
    answer = 0
    q = deque()

    for time, player in enumerate(players):
        while q:
            if q[0] <= time:
                q.popleft()
            else:
                break
        
        server_num = floor(player/m)

        while len(q) < server_num:
            q.append(time + k)
            answer += 1

    return answer
