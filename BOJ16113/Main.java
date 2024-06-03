package BOJ16113;

import java.io.*;
import java.util.*;

public class Main {
    static int N, colNum;
    static int[] dr = {1,0,-1,0}, dc = {0,1,0,-1};
    static String signal;
    static boolean[] visited;
    static String[] Digit = {
        "####.##.##.####", "#..#..#..#..#..", "###..#####..###",
        "###..####..####", "#.##.####..#..#", "####..###..####",
        "####..####.####", "###..#..#..#..#", "####.#####.####",
        "####.####..####"
    };

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        signal = br.readLine();
        colNum = N/5;
        visited = new boolean[N];
    }

    static boolean isValid(int r, int c) {
        if(r<0 || c<0 || r>=5 || c>=colNum)
            return false;
        if(signal.charAt(r*colNum+c)=='.')
            return false;
        if(visited[r*colNum+c])
            return false;
        return true;
    }

    static int checkDigit(char[] val) {
        for(int i = 0; i<10; i++) {
            boolean flag = true;
            for(int j = 0; j<15; j++) {
                if(val[j]!=Digit[i].charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if(flag)
                return i;
        }
        return -1;
    }

    static int BFS(int n) {
        char[] val = new char[15];
        Arrays.fill(val,'.');
        int r0 = n/colNum;
        int c0 = n%colNum;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(n);
        while(!q.isEmpty()) {
            int curR = q.peek()/colNum;
            int curC = q.peek()%colNum;
            for(int i = 0; i<4; i++) {
                int tmpR = curR + dr[i];
                int tmpC = curC + dc[i];
                if(isValid(tmpR, tmpC)) {
                    q.add(tmpR*colNum + tmpC);
                }
            }
            val[3*(curR-r0)+(curC-c0)] = '#';
            visited[q.poll()] = true;
        }
        return checkDigit(val);
    }

    static String solve() {
        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i<colNum; i++) {
            if(!visited[i] && signal.charAt(i)=='#')
                sb.append(BFS(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(solve());
    }
}
