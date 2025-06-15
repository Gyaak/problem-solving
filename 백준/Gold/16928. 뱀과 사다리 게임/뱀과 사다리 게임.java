import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int pos, cnt;

        Node(int pos, int cnt) {
            this.pos = pos;
            this.cnt = cnt;
        }
    }
    
    static final int SIZE = 100;

    static int N, M;
    static Integer[] graph = new Integer[SIZE];
    static boolean[] visited = new boolean[SIZE];

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int i = 0; i<SIZE; i++)
            graph[i] = -1;
        for(int i = 0 ; i<N+M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            graph[from] = to;
        }
    }

    static int solve() {
        Queue<Node> q = new ArrayDeque<>();

        q.add(new Node(0,0));
        visited[0] = true;
        while(!q.isEmpty()) {
            Node curNode = q.poll();
            for(int i = 1; i<=6; i++) {
                int newPos = graph[curNode.pos + i] != -1 ? graph[curNode.pos + i] : curNode.pos + i;
                if(visited[newPos]) continue;
                if(newPos == 99)    return curNode.cnt + 1;
                
                q.add(new Node(newPos, curNode.cnt + 1));
                visited[newPos] = true;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(solve());
    }
}
