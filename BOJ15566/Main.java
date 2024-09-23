package BOJ15566;

import java.io.*;
import java.util.*;

public class Main {

    static class Frog {
        int[] interest;
        List<Integer> pos;

        Frog(int[] interest, List<Integer> pos) {
            this.interest = interest;
            this.pos = pos;
        }
    }

    static class Log {
        int u, v, topic;

        Log(int u, int v, int topic) {
            this.u = u;
            this.v = v;
            this.topic = topic;
        }
    }

    static int N, M;
    static Frog[] frog;
    static Log[] log;
    
    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        frog = new Frog[N];
        log = new Log[M];
        int[][] interests = new int[N][4];

        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<4; j++) {
                interests[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken()) - 1;
            int p2 = Integer.parseInt(st.nextToken()) - 1;
            List<Integer> pos = new ArrayList<>();
            pos.add(p1);
            if(p1!=p2) {
                pos.add(p2);
            }
            frog[i] = new Frog(interests[i], pos);
        }

        for(int i = 0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int topic = Integer.parseInt(st.nextToken()) - 1;
            log[i] = new Log(u, v, topic);
        }
    }

    static boolean checkConversation(int[] pos) {
        for(Log l : log) {
            int p1 = pos[l.u];
            int p2 = pos[l.v];
            if(frog[p1].interest[l.topic]!=frog[p2].interest[l.topic]) {
                return false;
            }
        }
        return true;
    }

    static boolean DFS(int n, int[] pos) {
        if(n == N && checkConversation(pos)) {
            return true;
        }
        if(n<N) {
            for(int p : frog[n].pos) {
                if(pos[p]==-1) {
                    pos[p] = n;
                    if(DFS(n+1, pos)) {
                        return true;
                    }
                    pos[p] = -1;
                }
            } 
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        int[] pos = new int[N];
        Arrays.fill(pos,-1);
        if(DFS(0,pos)) {
            StringBuilder ans = new StringBuilder();
            ans.append("YES\n");
            for(int p : pos) {
                ans.append((p+1)+" ");
            }
            System.out.println(ans);
        } else {
            System.out.println("NO");
        }
    }
}
