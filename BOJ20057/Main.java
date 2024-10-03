package BOJ20057;

import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int r, c;
        double rate;

        Node(int r, int c, double rate) {
            this.r = r;
            this.c = c;
            this.rate = rate;
        }
    }

    static int N;
    static int[] dr = {0,1,0,-1}, dc = {-1,0,1,0};
    static int[][] A;
    static List<Node>[] rateInfo;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N][N];
        for(int i = 0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
    

    static void init() {
        rateInfo = new List[4];
        for(int i = 0; i<4; i++) {
            rateInfo[i] = new ArrayList<>();
        }
        rateInfo[0].add(new Node(0, -2, 0.05));
        rateInfo[0].add(new Node(1, -1, 0.1));
        rateInfo[0].add(new Node(-1, -1, 0.1));
        rateInfo[0].add(new Node(1, 0, 0.07));
        rateInfo[0].add(new Node(-1, 0, 0.07));
        rateInfo[0].add(new Node(2, 0, 0.02));
        rateInfo[0].add(new Node(-2, 0, 0.02));
        rateInfo[0].add(new Node(1, 1, 0.01));
        rateInfo[0].add(new Node(-1, 1, 0.01));
        
        rateInfo[1].add(new Node(2, 0, 0.05));
        rateInfo[1].add(new Node(1, 1, 0.1));
        rateInfo[1].add(new Node(1, -1, 0.1));
        rateInfo[1].add(new Node(0, 1, 0.07));
        rateInfo[1].add(new Node(0, -1, 0.07));
        rateInfo[1].add(new Node(0, 2, 0.02));
        rateInfo[1].add(new Node(0, -2, 0.02));
        rateInfo[1].add(new Node(-1, 1, 0.01));
        rateInfo[1].add(new Node(-1, -1, 0.01));

        rateInfo[2].add(new Node(0, 2, 0.05));
        rateInfo[2].add(new Node(1, 1, 0.1));
        rateInfo[2].add(new Node(-1, 1, 0.1));
        rateInfo[2].add(new Node(1, 0, 0.07));
        rateInfo[2].add(new Node(-1, 0, 0.07));
        rateInfo[2].add(new Node(2, 0, 0.02));
        rateInfo[2].add(new Node(-2, 0, 0.02));
        rateInfo[2].add(new Node(1, -1, 0.01));
        rateInfo[2].add(new Node(-1, -1, 0.01));

        rateInfo[3].add(new Node(-2, 0, 0.05));
        rateInfo[3].add(new Node(-1, 1, 0.1));
        rateInfo[3].add(new Node(-1, -1, 0.1));
        rateInfo[3].add(new Node(0, 1, 0.07));
        rateInfo[3].add(new Node(0, -1, 0.07));
        rateInfo[3].add(new Node(0, 2, 0.02));
        rateInfo[3].add(new Node(0, -2, 0.02));
        rateInfo[3].add(new Node(1, 1, 0.01));
        rateInfo[3].add(new Node(1, -1, 0.01));
    }

    static boolean isOutOfGrid(int r, int c) {
        if(r<0 || c<0 || r>=N || c>=N) {
            return true;
        }
        return false;
    }

    static int blow(int r, int c, int dirc) {
        int sand = A[r][c];
        int result = 0;
        for(Node info : rateInfo[dirc]) {
            int tmpR = r + info.r;
            int tmpC = c + info.c;
            int movedSand = (int)(A[r][c] * info.rate);
            sand -= movedSand;
            if(isOutOfGrid(tmpR, tmpC)) {
                result += movedSand;
            } else {
                A[tmpR][tmpC] += movedSand;
            }
        }
        if(isOutOfGrid(r + dr[dirc], c + dc[dirc])) {
            result += sand;
        } else {
            A[r + dr[dirc]][c + dc[dirc]] += sand;
        }
        A[r][c] = 0;
        return result;
    }

    static int solve() {
        int ans = 0;
        int r = N/2, c = N/2;
        int dirc = 0;
        int len = 1;
        while(true) {
            for(int i = 0; i<len; i++) {
                r += dr[dirc];
                c += dc[dirc];
                if(r<0 || c<0) {
                    return ans;
                }
                ans += blow(r, c, dirc);
            }
            dirc = (dirc + 1) % 4;
            if(dirc % 2 == 0) {
                len++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        setInput();
        init();
        System.out.println(solve());
    }
}
