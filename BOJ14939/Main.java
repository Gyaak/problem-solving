import java.io.*;

public class Main {
    static final int SIZE = 10;
    static boolean[][] board = new boolean[SIZE][SIZE];
    static int ans = Integer.MAX_VALUE;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i<SIZE; i++) {
            String row = br.readLine();
            for(int j = 0; j<SIZE; j++) {
                if(row.charAt(j)=='O') {
                    board[i][j] = true;
                }
            }
        }
    }

    static boolean isOff() {
        for(int i = 0; i<SIZE; i++) {
            for(int j = 0; j<SIZE; j++) {
                if(board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    static void turnOff(int r, int c) {
        if(r>0) {
            board[r-1][c] = !board[r-1][c];
        }
        if(r<SIZE-1) {
            board[r+1][c] = !board[r+1][c];
        }
        if(c>0) {
            board[r][c-1] = !board[r][c-1];
        }
        if(c<SIZE-1) {
            board[r][c+1] = !board[r][c+1];
        }
        board[r][c] = !board[r][c];
    }

    static int solve() {
        int cnt = 0;
        for(int i = 0; i<SIZE*SIZE; i++) {
            int r = i/10;
            int c = i%10;
            if(r>0 && board[r-1][c]) {
                turnOff(r, c);
                cnt++;
            }
        }
        if(isOff()) {
            return cnt;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(solve());
    }
}