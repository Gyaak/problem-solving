package BOJ11505;

import java.io.*;
import java.util.*;

public class Main {

    static final int MOD = 1_000_000_007;
    static int N, M, K, treeSize;
    static long[] tree;

    static void setInput(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        treeSize = 1;
        while(treeSize<N)
            treeSize <<= 1;
        treeSize <<= 1;
        tree = new long[treeSize];
        Arrays.fill(tree,-1);
        for(int i = 0; i<N; i++)
            tree[(treeSize>>1)+i] = Long.parseLong(br.readLine());
        for(int i = (treeSize>>1)+N; i<treeSize; i++)
            tree[i] = 1;
    }

    static long makeTree(int n) {
        if(tree[n]==-1) {
            tree[n] = makeTree(2*n)*makeTree(2*n+1);
            tree[n] %= MOD;
        }
        return tree[n];
    }

    static long read(int b, int c) {

        if(b==c)    return tree[b+(treeSize>>1)-1];
        b += (treeSize>>1)-1;
        c += (treeSize>>1)-1;
        long leftVal = tree[b], rightVal = tree[c];
        while((b>>1)!=(c>>1)) {
            if(b%2==0) {
                leftVal *= tree[b+1];
                leftVal %= MOD;
            } 
            b >>= 1;

            if(c%2==1) {
                rightVal *= tree[c-1];
                rightVal %= MOD;
            }
            c >>= 1;
        }
        return (leftVal*rightVal)%MOD;
    }

    static void update(int b, int c) {

        b += (treeSize>>1)-1;
        tree[b] = c;
        b >>= 1;
        while(b>0) {
            tree[b] = tree[2*b] * tree[2*b+1];
            tree[b] %= MOD;
            b >>= 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        setInput(br);
        makeTree(1);
        for(int i = 0; i<M+K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(a == 1)  update(b, c);
            else        bw.write(read(b, c)+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
