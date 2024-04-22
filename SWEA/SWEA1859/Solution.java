package SWEA.SWEA1859;

import java.io.*;
import java.util.*;

public class Solution {

    static class Node {
        int idx;
        long num;

        Node(int idx, long num) {
            this.idx = idx;
            this.num = num;
        }
    }

    static int N;
    static long[] price;

    static void setInput(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine());
        price = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++) {
            price[i] = Long.parseLong(st.nextToken());
        }
    }

    static long solve() {
        long sum = 0;
        Deque<Node> stack = new ArrayDeque<>();
        stack.addLast(new Node(0, 0));
        for(int i = 0; i<N; i++) {
            int tmpNum = 1;
            while(!stack.isEmpty() && price[stack.peekLast().idx]<=price[i]) {
                Node curNode = stack.pollLast();
                tmpNum += curNode.num;
                sum += (price[i]-price[curNode.idx]) * curNode.num;
            }
            stack.add(new Node(i,tmpNum));
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for(int i = 1; i<=T; i++) {
            setInput(br);
            bw.write("#"+i+" "+solve()+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
