package BOJ1111;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++)
            arr[i] = Integer.parseInt(st.nextToken());
    }

    static String solve() {
        if(N==1) {
            return "A";
        } else if (N==2) {
            if(arr[0]==arr[1])
                return String.valueOf(arr[0]);
            else
                return "A";
        } else {
            if(arr[1]-arr[0]==0) {
                for(int i = 2; i<N; i++) {
                    if(arr[i]!=arr[0])
                        return "B";
                }
                return String.valueOf(arr[0]);
            } else {
                int a = (arr[2]-arr[1])/(arr[1]-arr[0]);
                int b = (arr[1]*arr[1]-arr[2]*arr[0])/(arr[1]-arr[0]);
                for(int i = 0; i<N-1; i++) {
                    if(a*arr[i]+b!=arr[i+1])
                        return "B";
                }
                return String.valueOf(a*arr[N-1]+b);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        setInput();
        System.out.println(solve());
    }
}
