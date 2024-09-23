package BOJ2713;

import java.io.*;
import java.util.*;

public class Main {

    static final String[] CODE = {
        "00000", "00001", "00010", "00011", "00100",
        "00101", "00110", "00111", "01000", "01001",
        "01010", "01011", "01100", "01101", "01110",
        "01111", "10000", "10001", "10010", "10011",
        "10100", "10101", "10110", "10111", "11000",
        "11001", "11010"
    };

    static int R, C;
    static int[] dr = {0,1,0,-1}, dc = {1,0,-1,0};
    static String msg;

    static void setInput(BufferedReader br) throws IOException {
        String inputString = br.readLine();
        StringTokenizer st = new StringTokenizer(inputString);
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int buf = (R>9?3:2) + (C>9?3:2);
        msg = inputString.substring(buf);
    }

    static String convert() {
        int[][] arr = new int[R][C];
        StringBuilder tmpMsg = new StringBuilder();
        for(char c : msg.toCharArray()) {
            if(c==' ') {
                tmpMsg.append(CODE[0]);
            } else {
                tmpMsg.append(CODE[c - 'A' + 1]);
            }
        }
        String msgCode = tmpMsg.toString();
        int idx = 0, len = 0;
        int curR = 0, curC = 0, dirc = 0;
        while(true) {
            if(idx == msgCode.length()) {
                break;
            }
            arr[curR][curC] = msgCode.charAt(idx) - '0';
            if(dirc == 0) {
                if(curC == C-len-1) {
                    dirc++;
                }
            } else if(dirc == 1) {
                if(curR == R-len-1) {
                    dirc++;
                }
            } else if(dirc == 2) {
                if(curC == len) {
                    dirc++;
                }
            } else {
                if(curR == len+1) {
                    dirc = 0;
                    len++;
                }
            }
            curR += dr[dirc];
            curC += dc[dirc];
            idx++;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<arr.length; i++) {
            for(int j = 0; j<arr[0].length; j++) {
                sb.append(arr[i][j]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i<T; i++) {
            setInput(br);
            ans.append(convert());
            ans.append("\n");
        }
        System.out.print(ans);
    }
}
