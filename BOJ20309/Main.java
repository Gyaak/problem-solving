package BOJ20309;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=N; i++) {
            if((Integer.parseInt(st.nextToken())&1) != (i&1)) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
}
