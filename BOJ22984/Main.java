package BOJ22984;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        StringTokenizer st = new StringTokenizer(br.readLine());
        double prev = Double.parseDouble(st.nextToken());
        double answer = prev;
        while(st.hasMoreTokens()) {
            double cur = Double.parseDouble(st.nextToken());
            answer += prev*(1-cur) + (1-prev)*cur;
            answer += cur;
            prev = cur;
        }
        System.out.println(answer);
    }
}