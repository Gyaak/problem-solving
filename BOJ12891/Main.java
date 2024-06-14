package BOJ12891;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int lenS = Integer.parseInt(st.nextToken());
        int lenP = Integer.parseInt(st.nextToken());
        String S = br.readLine();
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int curA = 0, curC = 0, curG = 0, curT = 0;
        for(int i = 0; i<lenP; i++) {
            if(S.charAt(i)=='A')    curA++;
            if(S.charAt(i)=='C')    curC++;
            if(S.charAt(i)=='G')    curG++;
            if(S.charAt(i)=='T')    curT++;
        }
        int ans = 0;
        if(curA>=A && curC>=C && curG>=G && curT>=T)
            ans++;
        for(int i = lenP; i<lenS; i++) {
            if(S.charAt(i-lenP)=='A') curA--;
            if(S.charAt(i-lenP)=='C') curC--;
            if(S.charAt(i-lenP)=='G') curG--;
            if(S.charAt(i-lenP)=='T') curT--;
            if(S.charAt(i)=='A')    curA++;
            if(S.charAt(i)=='C')    curC++;
            if(S.charAt(i)=='G')    curG++;
            if(S.charAt(i)=='T')    curT++;
            if(curA>=A && curC>=C && curG>=G && curT>=T)
                ans++;
        }
        System.out.println(ans);
    }
}
