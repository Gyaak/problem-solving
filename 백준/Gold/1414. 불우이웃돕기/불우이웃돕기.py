import sys
from heapq import heappop as hpop, heapify

class Cable:
    def __init__(self, i, j, len):
        self.computer = (i,j)
        self.len = len
    
    def __str__(self):
        return f"[{self.computer[0]} - {self.computer[1]}] : {self.len}"
    
    def __lt__(self, other):
        return self.len < other.len

def parseInput():
    N = int(sys.stdin.readline().strip())
    cables:list[Cable] = []
    for i in range(N):
        row = list(map(str, sys.stdin.readline().strip()))
        for j in range(N):
            if row[j] == '0':
                continue # not connected
            elif row[j].lower() == row[j]:
                cable_length = ord(row[j]) - 96
            elif row[j].upper() == row[j]:
                cable_length = ord(row[j]) - 64 + 26
            cables.append(Cable(i, j, cable_length))
    return N, cables

def get_parent(idx, parents):
    cur = idx
    while parents[cur] != -1:
        cur = parents[cur]
    return cur

if __name__ == "__main__":
    N, cables = parseInput()
    heapify(cables)
    
    parents = [-1] * N

    donation = 0
    while len(cables)>0:
        c = hpop(cables)
        p1 = get_parent(c.computer[0], parents)
        p2 = get_parent(c.computer[1], parents)
        if p1 != -1 and p1 == p2:
            donation += c.len
        else:
            parents[p2] = c.computer[0]
    cnt = 0
    for p in parents:
        if p == -1:
            cnt += 1
    print(donation if cnt == 1 else -1)
    