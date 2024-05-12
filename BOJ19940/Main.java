package BOJ19940;

import java.io.*;

public class Main {
    
    static int[] solve(int n, boolean flag) {
        int[] num = new int[2];
        num[0] = n/10;
        num[1] = n%10;
        if(n%10>5) {
            num[0]++;
            num[1] -= 10;
        }
        if(!flag) {
            num[0] = -num[0];
            num[1] = -num[1];
        }
        return num;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i<T; i++) {
            int n = Integer.parseInt(br.readLine());
            int ADDH = n/60;
            boolean flag = true;
            n %= 60;
            if(n>35) {
                n = 60 - n;
                ADDH++;
                flag = false;
            }
            int[] num = solve(n, flag);
            bw.write(ADDH+" ");
            bw.write((num[0]>0?num[0]:0) + " ");
            bw.write((num[0]<0?-num[0]:0) + " ");
            bw.write((num[1]>0?num[1]:0) + " ");
            bw.write((num[1]<0?-num[1]:0) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
