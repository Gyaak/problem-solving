//package BOJ5582;

import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();
        System.out.println(findLCSLength(s1, s2));
    }

    private static int findLCSLength(String s1, String s2) {
        int len = 0;
        int[][] LCS = new int[s1.length()][s2.length()];
        for(int i = 0; i<s1.length(); i++) {
            for(int j = 0; j<s2.length(); j++) {
                if(s1.charAt(i)==s2.charAt(j)) {
                    LCS[i][j] = 1;
                    if(i>0 && j>0) {
                        LCS[i][j] += LCS[i-1][j-1];
                    }
                }
                len = Math.max(len, LCS[i][j]);
            }
        }
        return len;
    }
}
