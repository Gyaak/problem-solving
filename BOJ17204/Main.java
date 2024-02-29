package BOJ17204;

import java.io.*;

public class Main {
    static int N, K;
    static int[] a;
    static boolean[] visited;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inpuStrings = br.readLine().split(" ");
        N = Integer.parseInt(inpuStrings[0]);
        K = Integer.parseInt(inpuStrings[1]);
        a = new int[N];
        visited = new boolean[N];
        for(int i = 0; i<N; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }
        br.close();
    }

    public static void main(String[] args) throws IOException {
        setInput();
        int cur = 0;
        int M = 0;
        while(!visited[cur]) {
            if(cur==K) {
                System.out.println(M);
                return;
            }
            visited[cur] = true;
            cur = a[cur];
            M++;
        }
        System.out.println("-1");
    }
}
