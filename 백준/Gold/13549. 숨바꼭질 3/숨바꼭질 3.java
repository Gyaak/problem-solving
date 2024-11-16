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
            Node curNode = dq.pollFirst();
            if(curNode.idx == K) {
                return curNode.dist;
            }

            if(visited[curNode.idx]) {
                continue;
            } else {
                visited[curNode.idx] = true;
            }

            int tmpIdx = curNode.idx * 2;
            if(tmpIdx <= MAX && !visited[tmpIdx]) {
                dq.addFirst(new Node(tmpIdx, curNode.dist));
            }
            tmpIdx = curNode.idx + 1;
            if(tmpIdx <= MAX && !visited[tmpIdx]) {
                dq.addLast(new Node(tmpIdx, curNode.dist + 1));
            }
            tmpIdx = curNode.idx - 1;
            if(tmpIdx >= 0 && !visited[tmpIdx]) {
                dq.addLast(new Node(tmpIdx, curNode.dist + 1));
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(BFS());
    }
}
