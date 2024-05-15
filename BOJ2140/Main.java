package BOJ2140;

import java.io.*;

public class Main {

    static int N;
    static int[] dr = {-1,0,1}, dc = {-1,0,1};
    static int [][] board;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for(int i = 0; i<N; i++) {
            String inputString = br.readLine();
            for(int j = 0; j<N; j++) {
                if(inputString.charAt(j)=='#')
                    board[i][j] = -1;
                else
                    board[i][j] = inputString.charAt(j) - '0';
            }
        }
    }

    static int isValid(int r, int c) {
        // 주변 인접 8칸 확인
        for(int i = r-1; i<=r+1; i++) {
            for(int j = c-1; j<=c+1; j++) {
                if(i<0 || j<0 || i>=N || j>=N)
                    continue;
                if(board[i][j]==0)
                    return 0;
            }
        }
        // 지뢰를 배치하고 인접 칸들에 반영
        for(int i = r-1; i<=r+1; i++) {
            for(int j = c-1; j<=c+1; j++) {
                if(i<0 || j<0 || i>=N || j>=N)
                    continue;
                if(board[i][j]>0)
                    board[i][j]--;
            }
        }
        return 1;
    }

    static int findMine() {
        // 테두리에 닿지 않는 닫힌 칸들은 전부 지뢰가 있을 수 있음
        int num = N>3?(N-4)*(N-4):0;
        
        // 닫힌 칸 중 첫번째 줄
        for(int i = 1; i<N-1; i++)
            num += isValid(1, i);
        
            // 중간은 첫번째 값과 마지막 값만 확인하면됨
        for(int i = 1; i<N-1; i++) {
            num += isValid(i, 1);
            num += isValid(i, N-2);
        }
        
        // 닫힌 칸 중 마지막줄
        for(int i = 1; i<N-1; i++)
            num += isValid(N-2, i);

        return num;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(findMine());
    }
}