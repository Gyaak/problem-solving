package BOJ11689;

import java.io.*;
import java.util.*;

public class Main {

    static void SoE(int n, boolean[] primes) {
        Arrays.fill(primes,true);
        primes[0] = false;
        primes[1] = false;
        for(int i = 2; i<=n; i++) {
            if(primes[i]) {
                int cur = i+i;
                while(cur<=n) {
                    primes[cur] = false;
                    cur += i;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        int sqrtN = (int)Math.sqrt(n) + 1;
        boolean[] primes = new boolean[sqrtN+1];
        SoE(sqrtN, primes);
        long ans = n;
        long res = n;
        for(int i = 2; i<=sqrtN; i++) {
            if(primes[i] && n%i==0) {
                ans /= i;
                ans *= (i-1);
                while(res%i==0)
                    res /= i;
            }
        }
        ans = res==1?ans:(ans/res)*(res-1);
        System.out.println(ans);
    }
}
