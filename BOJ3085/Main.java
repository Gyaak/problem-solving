package BOJ3085;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    static int N;
    static int[] dr = {1,0}, dc = {0,1};
    static char[][] box;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        box = new char[N][N];
        for(int i = 0; i<N; i++) {
            String inputString = br.readLine();
            for(int j = 0; j<N; j++) {
                box[i][j] = inputString.charAt(j);
            }
        }
    }

    static int checkRow(int r) {
        int num = 0;
        int tmp = 1;
        char color = box[r][0];
        for(int i = 1; i<N; i++) {
            if(box[r][i]!=color) {
                color = box[r][i];
                num = Math.max(num,tmp);
                tmp = 0;
            }
            tmp++;
        }
        return Math.max(num,tmp);
    }

    static int checkCol(int c) {
        int num = 0;
        int tmp = 1;
        char color = box[0][c];
        for(int i = 1; i<N; i++) {
            if(box[i][c]!=color) {
                color = box[i][c];
                num = Math.max(num,tmp);
                tmp = 0;
            }
            tmp++;
        }
        return Math.max(num,tmp);
    }
    
    static boolean isValid(int r, int c) {
        if(r<0 || c<0 || r>=N || c>=N)
            return false;
        return true;
    }

    static void swap(int r1, int c1, int r2, int c2) {
        char tmp = box[r1][c1];
        box[r1][c1] = box[r2][c2];
        box[r2][c2] = tmp;
    }

    static int solve() {
        int num = 0;
        for(int i = 0; i<N; i++) {
            num = Math.max(num,checkRow(i));
            num = Math.max(num,checkCol(i));
        }
        for(int i = 0; i<N-1; i++) {
            for(int j = 0; j<N; j++) {
                if(box[i][j]!=box[i+1][j]){
                    swap(i,j,i+1,j);
                    num = Math.max(num,checkRow(i));
                    num = Math.max(num,checkRow(i+1));
                    num = Math.max(num,checkCol(j));
                    swap(i,j,i+1,j);
                }
                if(j<N-1) {
                    if(box[i][j]!=box[i][j+1]){
                        swap(i,j,i,j+1);
                        num = Math.max(num,checkRow(i));
                        num = Math.max(num,checkCol(j));
                        num = Math.max(num,checkCol(j+1));
                        swap(i,j,i,j+1);
                    }
                }
            }
        }
        return num;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(solve());
    }
}
