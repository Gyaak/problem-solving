package BOJ15311;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        if(N<1000) {
            ans.append(N+"\n");
            for(int i = 0; i<N-1; i++) {
                ans.append("1 ");
            }
            ans.append("1");
        } else {
            ans.append((N/1000+999)+"\n");
            for(int i = 0; i<999; i++) {
                ans.append("1 ");
            }
            for(int i = 0; i<N/1000-1; i++) {
                ans.append("1000 ");
            }
            ans.append("1000");
        }
        System.out.println(ans);
    }
}
