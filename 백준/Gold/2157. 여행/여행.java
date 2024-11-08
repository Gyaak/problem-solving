//package BOJ2157;

import java.io.*;
import java.util.*;

public class Main {
    static class Route {
        int dest, score;
        Route(int dest, int score) {
            this.dest = dest;
            this.score = score;
        }
    }
    static int N, M, K;
    static int[][] cache; // cache[i][j] := i번째 도시에 j번째로 도착했을 때, 최고 점수
    static List<Route>[] route;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        cache = new int[N][M];
        route = new List[N];
        for(int i = 0; i<N; i++) {
            route[i] = new ArrayList<>();
        }
        for(int i = 0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            // 여행은 현재 도시보다 번호가 큰 도시로만 진행
            if(a<b) {
                route[a].add(new Route(b, c));
            }
        }
    }

    static int solve() {
        for(Route r : route[0]) {
            cache[r.dest][1] = Math.max(cache[r.dest][1], r.score);
        }
        for(int j = 1; j<M-1; j++) {
            for(int i = 0; i<N; i++) {
                if(cache[i][j]==0) {
                    continue;
                }
                for(Route r : route[i]) {
                    int d = r.dest;
                    int s = r.score;
                    cache[d][j+1] = Math.max(cache[d][j+1], cache[i][j] + s);
                }
            }
        }
        int ans = 0;
        for(int c : cache[N-1]) {
            ans = Math.max(ans, c);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(solve());
    }
}
