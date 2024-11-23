//package BOJ2108;

import java.io.*;
import java.util.*;

public class Main {

    static class Frequency {
        int idx, num;
        Frequency(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        Frequency[] freq = new Frequency[8001];
        for(int i = 0; i<8001; i++) {
            freq[i] = new Frequency(i - 4000, 0);
        }
        int sum = 0;
        for(int i = 0; i < N; i++) {
            int val = Integer.parseInt(br.readLine());
            arr[i] = val;
            sum += val;
            freq[val+4000].num++;
        }
        Arrays.sort(arr);
        Arrays.sort(freq, (o1, o2) -> {
            if (o1.num != o2.num) {
                return Integer.compare(o2.num, o1.num);
            }
            return Integer.compare(o1.idx, o2.idx);
        });

        System.out.println(Math.round(((double)sum / N)));
        System.out.println(arr[N/2]);
        System.out.println((N==1 || freq[0].num != freq[1].num)? freq[0].idx : freq[1].idx);
        System.out.println(arr[N-1]-arr[0]);
    }
}