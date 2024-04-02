package BOJ17142;

import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int first, second, val;

        Node(int first, int second, int val) {
            this.first = first;
            this.second = second;
            this.val = val;
        }
    }
    static int N, M, ans = Integer.MAX_VALUE;
    static final int[] dr = {1,0,-1,0}, dc = {0,1,0,-1};
    static int[][] lab;
    static int[][][] infection;
    static boolean[] selected;
    static ArrayList<Node> virus = new ArrayList<>(), wall = new ArrayList<>();

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lab = new int[N][N];
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
                if(lab[i][j]==1)    wall.add(new Node(i, j, 0));
                if(lab[i][j]==2)    virus.add(new Node(i, j, 0));
            }
        }

        infection = new int[virus.size()][N][N];
        selected = new boolean[virus.size()];
        for(int i = 0; i<virus.size(); i++)
            for(int j = 0; j<N; j++)
                Arrays.fill(infection[i][j],-1);
        for(int i = 0; i<virus.size(); i++) {
            infection[i][virus.get(i).first][virus.get(i).second] = 0;
            for(Node p : wall)
                infection[i][p.first][p.second] = -2;
         }
        br.close();
    }

    static boolean isValid(int r, int c) {
        if(r<0 || c<0 || r>=N || c>=N)
            return false;
        return true;
    }

    static void BFS(int n) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(virus.get(n));
        while(!q.isEmpty()) {
            Node cur = q.poll();
            for(int i = 0; i<4; i++) {
                int tmpR = cur.first + dr[i];
                int tmpC = cur.second + dc[i];
                if(isValid(tmpR, tmpC)&&infection[n][tmpR][tmpC]==-1) {
                    infection[n][tmpR][tmpC] = cur.val + 1;
                    q.add(new Node(tmpR, tmpC, cur.val+1));
                }
            }
        }
    }

    static int calcTime() {
        int time = 0;
        for(int i = 0; i<N; i++) {
            for(int j = 0; j<N; j++) {
                if(lab[i][j]==1)   continue;
                int tmpVal = Integer.MAX_VALUE;
                for(int k = 0; k<virus.size(); k++) {
                    if(infection[k][i][j]==0) {
                        tmpVal = 0;
                        break;
                    }
                    if(!selected[k])    continue;
                    if(infection[k][i][j]>=0) 
                        tmpVal = Math.min(tmpVal,infection[k][i][j]);
                }
                time = Math.max(time, tmpVal);
            }
        }
        return time;
    }

    static void DFS(int idx, int num) {
        if(num==M) {
            ans = Math.min(ans,calcTime());
            return;
        }
        for(int i = idx; i<virus.size(); i++) {
            selected[i] = true;
            DFS(i+1, num+1);
            selected[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        setInput();
        for(int i = 0; i<virus.size(); i++)
            BFS(i);
        DFS(0,0);
        System.out.println(ans==Integer.MAX_VALUE?-1:ans);
    }
}
