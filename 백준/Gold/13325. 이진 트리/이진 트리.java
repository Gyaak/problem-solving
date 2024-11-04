//package BOJ13325;

import java.io.*;
import java.util.*;

public class Main {
    static int k, N;
    static int[] edges, cache;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        N = 1<<(k+1);
        edges = new int[N];
        cache = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 2; i<N; i++) {
            edges[i] = Integer.parseInt(st.nextToken());
        }
    }

    static int solve() {
        // edge[i] : i번째 노드와 부모를 잇는 엣지의 가중치
        // cache[i] : i번째 로드에서 리프노드까지의 가중치 합
        for(int i = N/2 - 1; i>0; i--) {
            int leftWeight = cache[2 * i] + edges[2 * i];
            int rightWeight = cache[2 * i + 1] + edges[2 * i + 1];
            if(leftWeight < rightWeight) {
                cache[i] = rightWeight;
                edges[2 * i] += rightWeight - leftWeight;
            } else {
                cache[i] = leftWeight;
                edges[2 * i + 1] += leftWeight - rightWeight;
            }
        }
        int ans = 0;
        for(int w : edges) {
            ans += w;
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(solve());
    }
}
