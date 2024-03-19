package BOJ9252;

import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();
        br.close();
        
        int[][] cache = new int[s1.length()+1][s2.length()+1];
        for(int i = 1; i<=s1.length(); i++) {
            for(int j = 1; j<=s2.length(); j++) {
                cache[i][j] = Math.max(cache[i-1][j],cache[i][j-1]);
                if(s1.charAt(i-1)==s2.charAt(j-1))
                    cache[i][j] = Math.max(cache[i][j], cache[i-1][j-1]+1);
            }
        }
        
        int curR = s1.length();
        int curC = s2.length();
        char[] LCS = new char[cache[curR][curC]];
        while(cache[curR][curC]!=0) {
            if(cache[curR-1][curC]==cache[curR][curC]) {
                curR--;
            } else if(cache[curR][curC]==cache[curR][curC-1]) {
                curC--;
            } else {
                LCS[cache[curR][curC]-1] = s1.charAt(curR-1);
                curR--;
                curC--;
            }
        }
        System.out.println(LCS.length + "\n" + String.valueOf(LCS));
    }    
}
