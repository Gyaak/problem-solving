class Solution {
    
    private static int N;
    private static int[][] puzzle;
    
    public int solution(int[][] clockHands) {
        N = clockHands.length;
        return solve(clockHands);
    }
    
    private int solve(int[][] clockHands) {
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i<(1<<(2*N)); i++) {
            int num = 0;
            puzzle = new int[N][N];
            for(int r = 0; r<N; r++) {
                for(int c = 0; c<N; c++) {
                    puzzle[r][c] = clockHands[r][c];
                }
            }
            int val = i;
            for(int j = 0; j<N; j++) {
                rotate(0, j, val % 4);
                num += val % 4;
                val /= 4;
            }
            int tmp = greedy();
            if(tmp >= 0) {
                ans = Math.min(ans, num + tmp);
            }
        }
        return ans;
    }
    
    private int greedy() {
        int num = 0;
        for(int i = 1; i<N; i++) {
            for(int j = 0; j<N; j++) {
                int val = (4 - puzzle[i-1][j]) % 4;
                rotate(i, j, val);
                num += val;
            }
        }
        for(int i = 0; i<N; i++) {
            if(puzzle[N-1][i] != 0) {
                return -1;
            }
        }
        return num;
    }
    
    private void rotate(int r, int c, int num) {
        if(r < N-1) {
            puzzle[r+1][c] = (puzzle[r+1][c] + num) % 4;
        }
        if(c < N-1) {
            puzzle[r][c+1] = (puzzle[r][c+1] + num) % 4;
        }
        if(r > 0) {
            puzzle[r-1][c] = (puzzle[r-1][c] + num) % 4;
        }
        if(c > 0) {
            puzzle[r][c-1] = (puzzle[r][c-1] + num) % 4;
        }
        puzzle[r][c] = (puzzle[r][c] + num) % 4;
    }
}