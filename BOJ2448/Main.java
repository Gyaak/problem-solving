package BOJ2448;

import java.io.*;

public class Main {

    static StringBuilder[] grow(int h, StringBuilder[] board) {
        int half = h>>1;
        StringBuilder space = new StringBuilder();
        for(int i = 0; i<half; i++) {
            space.append(" ");
        }
        for(int i = h-1; i>=half; i--) {
            board[i].append(space);
            board[i].append(board[i-half]);
            board[i].append(space);
        }
        for(int i = half-1; i>=0; i--) {
            board[i].append(board[i]);
        }
        return board;
    } 

    static void printStar(int n) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder[] board = new StringBuilder[n];
        for(int i = 0; i<n; i++) {
            // 미리 공간을 할당하여 시간 단축 288ms -> 236ms
            board[i] = new StringBuilder(n<<1);
        }
        board[0].append("***** ");
        board[1].append(" * *  ");
        board[2].append("  *   ");
        for(int i = 6; i<=n; i*=2) {
            grow(i, board);
        }
        // StringBuilder로 합쳐서 System.out.print하는것보다 BufferdWriter가 빠름
        // 344ms -> 288ms
        for(int i = n-1; i>=0; i--) {
            bw.write(board[i].toString());
            bw.write("\n");
        }
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        printStar(Integer.parseInt(br.readLine()));
    }
}
