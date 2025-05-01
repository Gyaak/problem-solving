from typing import List, Tuple
import sys

dr = [-1, -1, 0, 1, 1, 1, 0, -1]
dc = [0, 1, 1, 1, -1, -1, -1, 0]

def setInput() -> Tuple[int, List[List[int]]]:
    N = int(sys.stdin.readline().strip())

    board = [
        list(map(int, sys.stdin.readline().strip().split()))
        for _ in range(N)
    ]

    return N, board

def get_possible_jump_pos(row:int, col:int, board:List[List[int]])->List[int]:
    pos = []

    for i in range(8):
        if board[row+dr[i]][col+dc[i]] == 2 and board[row+2*dr[i]][col+2*dc[i]] == 0:
            pos.append(i)

    return pos

def DFS(remain_stone:int, N:int, board:List[List[int]]) -> bool:
    if remain_stone == 1:
        return True

    for row in range(N):
        for col in range(N):
            if board[row][col] == 2:
                pos = get_possible_jump_pos(row, col, board)
                for p in pos:
                    board[row][col] = 0
                    board[row+dr[p]][col+dc[p]] = 0
                    board[row+2*dr[p]][col+2*dc[p]] = 2
                    if DFS(remain_stone-1, N, board):
                        return True
                    board[row][col] = 2
                    board[row+dr[p]][col+dc[p]] = 2
                    board[row+2*dr[p]][col+2*dc[p]] = 0

    return False

def solve():
    N, board = setInput()
    stone_cnt = sum(cell == 2 for row in board for cell in row)
    print("Possible" if DFS(stone_cnt, N, board) else "Impossible")

if __name__ == "__main__":
    solve()
