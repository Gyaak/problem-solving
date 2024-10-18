package BOJ2412;

import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int dist;
        long val;

        Node(long val, int dist) {
            this.val = val;
            this.dist = dist;
        }
    }
    static final long MOD = 10_000_000;
    static int n;
    static long T;
    static Set<Long> pos;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        pos = new HashSet<>();
        for(int i = 0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            pos.add(x + y * MOD);
        }
    }

    static int BFS() {
        Queue<Node> q = new ArrayDeque<>();
        Set<Long> visited = new HashSet<>();
        q.add(new Node(0, 0));
        visited.add(0L);
        while(!q.isEmpty()) {
            Node curNode = q.poll();
            long curX = curNode.val % MOD;
            long curY = curNode.val / MOD;
            if(curY == T) {
                return curNode.dist;
            }
            for(int i = -2; i<=2; i++) {
                for(int j = -2; j<=2; j++) {
                    long val = curX + i + (curY + j) * MOD;
                    if(pos.contains(val) && !visited.contains(val)) {
                        q.add(new Node(val, curNode.dist + 1));
                        visited.add(val);
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(BFS());
    }
}
