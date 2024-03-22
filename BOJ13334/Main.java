package BOJ13334;

import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int h, o;
        Node(int h, int o) {
            this.h = h;
            this.o = o;
        }
    }
    static int n, d;
    static Node[] nodes;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nodes = new Node[n];
        for(int i = 0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int o = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(Math.min(h,o), Math.max(h,o));
        }
        d = Integer.parseInt(br.readLine());
        Arrays.sort(nodes,(i,j)->i.o==j.o?(i.h-j.h):(i.o-j.o));
    }
    
    static int solve() {
        PriorityQueue<Node> pq = new PriorityQueue<>((i,j)->i.h==j.h?(i.o-j.o):(i.h-j.h));
        int num = 0;
        for(int i = 0; i<n; i++) {
            if(nodes[i].o-nodes[i].h>d)
                continue;
            while(!pq.isEmpty() && pq.peek().h<nodes[i].o-d) {
                pq.poll();
            }
            pq.add(nodes[i]);
            num = Math.max(num, pq.size());
        }
        return num;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(solve());
    }
}