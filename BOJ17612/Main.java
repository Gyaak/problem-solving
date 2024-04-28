package BOJ17612;

import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class Main {

    static class Cart {
        int id, endTime, counterNum;

        Cart(int id, int endTime, int counterNum) {
            this.id = id;
            this.endTime = endTime;
            this.counterNum = counterNum;
        }
    }

    static class Counter {
        int num, endTime;

        Counter(int num, int endTime) {
            this.num = num;
            this.endTime = endTime;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        long ans = 0;

        PriorityQueue<Counter> counterQueue = new PriorityQueue<>(
            (Counter c1, Counter c2)-> c1.endTime==c2.endTime?c1.num-c2.num:c1.endTime-c2.endTime);

        PriorityQueue<Cart> cartQueue = new PriorityQueue<>(
            (Cart c1, Cart c2) -> c1.endTime==c2.endTime?c2.counterNum-c1.counterNum:c1.endTime-c2.endTime);

        for(int i = 1; i<=K; i++)
            counterQueue.add(new Counter(i, 0));

        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            Counter emptyCounter = counterQueue.poll();
            int curTime = emptyCounter.endTime + w;
            cartQueue.add(new Cart(id, curTime, emptyCounter.num));
            counterQueue.add(new Counter(emptyCounter.num, curTime));
        }

        for(long i = 1; i<=N; i++)
            ans += i*cartQueue.poll().id;

        System.out.println(ans);
        br.close();
    }
}