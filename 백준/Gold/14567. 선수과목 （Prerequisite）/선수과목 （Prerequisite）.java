//package BOJ14567;

import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int num, semester;

        Node(int num, int semester) {
            this.num = num;
            this.semester = semester;
        }
    }

    static int N, M;
    static int[] hasPreCourse;
    static List<Integer>[] preCourse;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        preCourse = new List[N];
        hasPreCourse = new int[N];
        for(int i = 0; i<N; i++) {
            preCourse[i] = new ArrayList<>();
        }
        for(int i = 0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;
            preCourse[A].add(B);
            hasPreCourse[B]++;
        }
    }

    static int[] topologySort() {
        int[] result = new int[N];
        Queue<Node> q = new ArrayDeque<>();
        for(int i = 0; i<N; i++) {
            if(hasPreCourse[i] == 0) {
                q.add(new Node(i, 1));
            }
        }
        while(!q.isEmpty()) {
            int num = q.peek().num;
            int semester = q.poll().semester;
            result[num] = semester;
            for(int p : preCourse[num]) {
                hasPreCourse[p]--;
                if(hasPreCourse[p] == 0) {
                    q.add(new Node(p, semester + 1));
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        StringBuilder sb = new StringBuilder();
        for(int n : topologySort()) {
            sb.append(n).append(" ");
        }
        System.out.println(sb);
    }
}
