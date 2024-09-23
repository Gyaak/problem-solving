package BOJ14653;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, K, Q;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        int checked = 1, tmp = 0, prev = -1;
        boolean allRead = false;
        for(int i = 1; i<=K; i++) {
            String[] msg = br.readLine().split(" ");
            int num = Integer.parseInt(msg[0]);
            int person = msg[1].charAt(0) - 'A';
            if(i<Q) {
                if(num==prev) {
                    tmp |= 1<<person;
                } else {
                    tmp = 1<<person;
                    prev = num;
                }
            } else {
                if(num==0) {
                    allRead = true;
                    break;
                }
                if(i==Q && prev==num) {
                    checked |= tmp;
                }
                checked |= 1<<person;
            }
        }
        if(allRead) {
            System.out.println(-1);
        } else {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i<N; i++) {
                if(((1<<i)&checked) == 0) {
                    sb.append((char)(i+'A') + " ");
                }
            }
            System.out.println(sb);
        }
    }
}
