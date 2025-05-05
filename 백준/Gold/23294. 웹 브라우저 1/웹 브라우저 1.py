import sys
from collections import deque

N = None
Q = None
C = None
CAP = None
tasks = []
cur_page = None
total_cap = 0
back_q = deque()
front_q = deque()

def set_input():
    global N, Q, C, CAP, tasks
    N, Q, C = map(int, sys.stdin.readline().split())
    CAP = list(map(int, sys.stdin.readline().split()))
    for _ in range(Q):
        tasks.append(sys.stdin.readline().split())

def compress():
    global CAP, total_cap, back_q
    
    if len(back_q) == 0:
        return

    back_q.appendleft(0)
    while(e := back_q.pop()):
        if back_q[0] == e:
            total_cap -= CAP[e-1]
        else:
            back_q.appendleft(e)

def move_back():
    global cur_page, back_q, front_q

    if len(back_q) == 0:
        return
    
    front_q.appendleft(cur_page)
    cur_page = back_q.pop()

def move_front():
    global cur_page, back_q, front_q

    if len(front_q) == 0:
        return
    
    back_q.append(cur_page)
    cur_page = front_q.popleft()

def access_page(new_page:int):
    global C, CAP, total_cap, cur_page, back_q, front_q

    while len(front_q) > 0:
        total_cap -= CAP[front_q.pop()-1]
    
    if cur_page:
        back_q.append(cur_page)

    cur_page = new_page
    total_cap += CAP[new_page-1]

    while total_cap > C:
        total_cap -= CAP[back_q.popleft()-1]

def run(task:list['str']):
    if task[0] == 'B':
        move_back()
    elif task[0] == 'F':
        move_front()
    elif task[0] == 'C':
        compress()
    else:
        new_page = int(task[1])
        access_page(new_page)

def solve():
    global tasks, cur_page, back_q, front_q
    set_input()
    for task in tasks:
        run(task)

    print(cur_page)

    if back_q:
        print(" ".join(str(x) for x in reversed(back_q)))
    else:
        print(-1)
    
    if front_q:
        print(" ".join(str(x) for x in front_q))
    else:
        print(-1)

if __name__=="__main__":
    solve()
