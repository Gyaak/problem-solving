//package BOJ13549;

import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int idx, dist;

        Node(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }
    }
    
    static int N, K;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }

    static int BFS() {
        final int MAX = 100_000;
        Deque<Node> dq = new ArrayDeque<>();
        boolean[] visited = new boolean[MAX+1];
        dq.add(new Node(N,0));

        while(!dq.isEmpty()) {
            int idx = dq.peekFirst().idx;
            int dist = dq.pollFirst().dist;
            if(idx == K) {
                return dist;
            }

            if(visited[idx]) {
                continue;
            } else {
                visited[idx] = true;
            }

            int tmpIdx = idx * 2;
            if(tmpIdx <= MAX && !visited[tmpIdx]) {
                dq.addFirst(new Node(tmpIdx, dist));
            }
            tmpIdx = idx + 1;
            if(tmpIdx <= MAX && !visited[tmpIdx]) {
                dq.addLast(new Node(tmpIdx, dist + 1));
            }
            tmpIdx = idx - 1;
            if(tmpIdx >= 0 && !visited[tmpIdx]) {
                dq.addLast(new Node(tmpIdx, dist + 1));
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(BFS());
    }
}
