from collections import deque

arr = None
alpha_dict = None

def get_idx(target:str):
    return ord(target) - ord("A")

def preprocess_input(storage:list):
    global arr, alpha_dict
    n = len(storage)
    m = len(storage[0])
    arr = [[-1] * (m+2) for _ in range(n+2)]
    alpha_dict = { i : [] for i in range(26) }
    for i in range(n):
        for j in range(m):
            idx = get_idx(storage[i][j])
            arr[i+1][j+1] = idx 
            alpha_dict[idx].append((i+1,j+1))

def call_forklift(target:str):
    global arr

    dr = [1, 0, -1, 0]
    dc = [0, 1, 0, -1]

    target_idx = get_idx(target)

    q = deque()
    q.append((0,0))
    visited = [[False] * len(arr[0]) for _ in range(len(arr))]
    visited[0][0] = True

    while q:
        cur = q.popleft()

        for i in range(4):
            r = cur[0] + dr[i]
            c = cur[1] + dc[i]

            if r<0 or r>=len(arr) or c<0 or c>=len(arr[0]):
                continue
            if visited[r][c]:
                continue

            if arr[r][c] == -1:
                q.append((r,c))
            elif arr[r][c] == target_idx:
                arr[r][c] = -1

            visited[r][c] = True

def call_crain(target:str):
    global arr, alpha_dict

    idx = get_idx(target)

    if idx not in alpha_dict:
        return

    ll = alpha_dict[idx]
    alpha_dict[idx] = []

    for l in ll:
        arr[l[0]][l[1]] = -1

def solution(storage, requests):
    global arr
    answer = 0
    preprocess_input(storage)

    for request in requests:
        if len(request) < 2:
            call_forklift(request)
        else:
            call_crain(request[0])
    
    for row in arr:
        for r in row:
            if r > -1:
                answer += 1
    return answer
