package BOJ3830;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] diff, root;

    // n의 루트를 탐색하며 루트와 n의 무게 갱신
    static int findRoot(int n) {
        if(root[n]==-1)
            return n;
        int r = findRoot(root[n]);
        diff[n] += diff[root[n]];
        root[n] = r;
        return root[n];
    }

    // b의 루트를 a의 루트로 변경(합치기)
    static void update(int a, int b, int w) {
        int rootA = findRoot(a);
        int rootB = findRoot(b);
        diff[rootB] = diff[a] - diff[b] + w;
        root[rootB] = rootA;
        root[rootA] = -1;
    }

    static String select(int a, int b) {
        if(findRoot(a)!=findRoot(b))
            return "UNKNOWN\n";
        return (diff[b]-diff[a])+"\n";
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if(N==0 && M==0)  break;
            diff = new int[N+1];
            root = new int[N+1];
            Arrays.fill(diff,0);
            Arrays.fill(root,-1);
            for(int i = 0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                char type = st.nextToken().charAt(0);
                if(type == '!') {
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    int w = Integer.parseInt(st.nextToken());
                    update(a,b,w);
                } else {
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    bw.write(select(a,b));
                }
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
