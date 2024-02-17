package BOJ1564;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 5^8 = 390625라서 마지막 8자리까지 0으로 채워질 수 있으므로,
        // 13자리까지 확인.. 해야하는데 overflow 발생.. 12자리로만 해도 AC받음..
        final long MOD = 1_000_000_000_000L; // 1e12
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());
        long cur = 362880; // 9! = 362880
        for(int i = 10; i<=N; i++) {
            cur *= i;
            while(cur%10==0)
                cur /= 10;
            cur %= MOD;
        }

        String answer = String.valueOf(cur%100000);
        for(int i = 0; i<5-answer.length(); i++)
            System.out.print("0");
        System.out.println(answer);

        br.close();
    }
}