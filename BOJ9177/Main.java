package BOJ9177;

import java.io.*;
import java.util.*;

public class Main {

    static String word1, word2, word3;
    static int[][] cache;
    
    static void setInput(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        word1 = st.nextToken();
        word2 = st.nextToken();
        word3 = st.nextToken();
        cache = new int[word1.length()+1][word2.length()+1];
        if(word1.charAt(0) == word3.charAt(0)) {
            cache[1][0] = 1;
        }
        if(word2.charAt(0) == word3.charAt(0)) {
            cache[0][1] = 1;
        }
    }

    static boolean Cache(int idx1, int idx2, int idx3) {
        if(cache[idx1][idx2] == 0) {
            boolean flag = false;
            if(idx1>0 && word1.charAt(idx1-1) == word3.charAt(idx3-1)) {
                flag |= Cache(idx1-1, idx2, idx3-1);
            }
            if(idx2>0 && word2.charAt(idx2-1) == word3.charAt(idx3-1)) {
                flag |= Cache(idx1, idx2-1, idx3-1);
            }
            cache[idx1][idx2] = flag ? 1 : -1;
        }
        return (cache[idx1][idx2] > 0) ? true : false;
    }

    static String makeOutputString(int num, boolean result) {
        return "Data set " + (num+1) + ": " + (result ? "yes\n" : "no\n");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for(int i=0; i<T; i++) {
            setInput(br);
            bw.write(makeOutputString(i, Cache(word1.length(), word2.length(), word3.length())));
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
