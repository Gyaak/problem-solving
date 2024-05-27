package BOJ21315;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] cards;
    static Deque<Integer> dq = new ArrayDeque<>();

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cards = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++)
            cards[i] = Integer.parseInt(st.nextToken());
    }

    static int[] shuffle(int K, int[] arr) {
        int lastIdx = N-1;
        for(int i = K; i>=0; i--) {
            int len = (1<<i);
            for(int j = lastIdx; j>lastIdx-len; j--)
                dq.addFirst(arr[j]);
            for(int j = lastIdx-len; j>=0; j--)
                arr[j+len] = arr[j];
            for(int j = 0; j<len; j++)
                arr[j] = dq.pollFirst();
            lastIdx = len - 1;
        }
        return arr;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        int[] original = new int[N];
        for(int i = 0; i<N; i++)
            original[i] = i+1;
        for(int i = 1; (1<<i)<N; i++) {
            for(int j = 1; (1<<j)<N; j++) {
                int[] tmp = Arrays.copyOf(original, N);
                shuffle(i, tmp);
                shuffle(j, tmp);
                boolean flag = true;
                for(int k = 0; k<N; k++) {
                    if(cards[k]!=tmp[k]) {
                        flag = false;
                        break;
                    }
                }
                if(flag) {
                    System.out.println(i + " " + j);
                    return;
                }
            }
        }
    }
}
