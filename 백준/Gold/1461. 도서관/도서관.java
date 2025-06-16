//package boj1461;

import java.io.*;
import java.util.*;

public class Main{
    static int N, M;
    static List<Integer> books = new ArrayList<>();
    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++)
            books.add(Integer.parseInt(st.nextToken()));
        books.add(0);
        books.sort((i,j)->i-j);
    }

    public static void main(String[] args) throws IOException {
        setInput();
        int zeroPos = 0;
        for(int b: books) {
            if(b == 0)  break;
            zeroPos++;
        }
        int ans = 0;
        for(int i = 0; i<zeroPos; i+=M) {
            ans -= books.get(i) * 2;
        }
        for(int i = N; i>zeroPos; i-=M) {
            ans += books.get(i) * 2;
        }
        ans -= Math.max(Math.abs(books.get(0)), books.get(N));

        System.out.println(ans);
    }
}