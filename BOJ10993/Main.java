package BOJ10993;

import java.io.*;

public class Main {

    static char[][] board;

    static void draw(int n, int r, int c) {
        if(n == 1) {
            board[r][c] = '*';
            return;
        }
        int H = (1<<n)-1;
        int W = (1<<(n+1))-3;
        if(n%2==0) {
            for(int i = 0; i<H; i++) {
                for(int j = 0; j<W; j++) {
                    if(i==0 || i==j || i+j==W-1)
                        board[r+i][c+j] = '*';
                    else if(i+j<=W)
                        board[r+i][c+j] = ' ';
                }
            }
            draw(n-1, r+1, c+W/4+1);
        } else {
            for(int i = 0; i<H; i++) {
                for(int j = 0; j<W; j++) {
                    if(i==H-1 || j-i==W-H || i+j==H-1)
                        board[r+i][c+j] = '*';
                    else if(j-i<=W-H)
                        board[r+i][c+j] = ' ';
                }
            }
            draw(n-1, r+H/2, c+W/4+1);
        }
        
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int H = (1<<n)-1;
        int W = (1<<(n+1))-3;
        board = new char[H][W];
        draw(n, 0, 0);
        for(int i = 0; i<H; i++) {
            for(int j = 0; j<W; j++) {
                if(board[i][j]=='\u0000')   break;
                bw.write(board[i][j]);
            }
            bw.write("\n");    
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
