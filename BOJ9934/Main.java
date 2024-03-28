package BOJ9934;

import java.io.*;
import java.util.*;

public class Main {
    static int K;
    static int[] num;
    static ArrayList<Integer>[] tree;

    static void makeTree(int st, int ed, int depth) {
        if(st>ed)   return;
        int mid = (st+ed)/2;
        tree[depth].add(num[mid]);
        makeTree(st, mid-1, depth+1);
        makeTree(mid+1, ed, depth+1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        num = new int[1<<K];
        tree = new ArrayList[K];
        for(int i = 0; i<K; i++)
            tree[i] = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i<(1<<K); i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        makeTree(1, (1<<K)-1, 0);

        StringBuilder ans = new StringBuilder("");
        for(int i = 0; i<K; i++) {
            for(int n : tree[i])
                ans.append(n+" ");
            ans.append("\n");
        }
        System.out.print(ans);
        br.close();
    }
}
