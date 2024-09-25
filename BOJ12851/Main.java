package BOJ12851;

import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int pos, time;

        Node(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }
    }

    static final int MAX = 100_001;
    static int N, K;
    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }

    static int[] BFS() {
        Queue<Node> q = new ArrayDeque<>();
        int[] visited = new int[MAX];
        Arrays.fill(visited, Integer.MAX_VALUE);
        visited[N] = 0;
        q.add(new Node(N, 0));
        while(!q.isEmpty()) {
            Node cur = q.poll();
            if(cur.pos == K) {
                int[] ans = new int[2];
                ans[0] = cur.time;
                ans[1] = 1;
                while(!q.isEmpty() && q.peek().time == ans[0]) {
                    if(q.poll().pos == K) {
                        ans[1]++;
                    }
                }
                return ans;
            }

            if(cur.pos+1 < MAX && cur.time<=visited[cur.pos+1]) {
                q.add(new Node(cur.pos+1, cur.time+1));
                visited[cur.pos+1] = cur.time;
            }
            if(cur.pos-1 >= 0 && cur.time<=visited[cur.pos-1]) {
                q.add(new Node(cur.pos-1, cur.time+1));
                visited[cur.pos-1] = cur.time;
            }
            if((cur.pos<<1) < MAX && cur.time<=visited[cur.pos<<1]) {
                q.add(new Node(cur.pos<<1, cur.time+1));
                visited[cur.pos<<1] = cur.time;
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        int[] ans = BFS();
        System.out.println(ans[0]);
        System.out.println(ans[1]);
    }
}
