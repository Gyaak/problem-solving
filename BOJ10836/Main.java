package BOJ10836;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[2*M-1];
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int num0 = Integer.parseInt(st.nextToken());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            if(num0!=2*M-1) arr[num0]++;
            if(num2!=0)     arr[num0+num1]++;
        }
        int cur = 1;
        for(int i = 0; i<2*M-1; i++) {
            arr[i] += cur;
            cur = arr[i];
        }
        for(int i = 0; i<M; i++) {
            bw.write(arr[M-1-i]+" ");
            for(int j = 1; j<M; j++)
                bw.write(arr[M-1+j]+" ");
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}