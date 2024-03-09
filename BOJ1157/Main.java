package BOJ1157;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        br.close();
        int[] num = new int[26];
        for(int i = 0; i<word.length(); i++) {
            int l = word.charAt(i);
            if(l>96)    l -= 32;
            num[l-65]++;
        }
        boolean flag = false;
        int maxIdx = 0;
        int maxVal = num[0];
        for(int i = 1; i<26; i++) {
            if(maxVal==num[i])
                flag = true;
            if(maxVal<num[i]) {
                flag = false;
                maxIdx = i;
                maxVal = num[i];
            }
        }
        System.out.println(flag?"?":(char)(maxIdx+'A'));
    }
}
