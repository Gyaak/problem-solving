package BOJ1744;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Integer> notPositive, positive;

    static int setInput() throws IOException {
        int init = 0;
        notPositive = new ArrayList<>();
        positive = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i = 0; i<N; i++) {
            int val = Integer.parseInt(br.readLine());
            // 1은 무조건 묶지않고 다 더함...
            if(val == 1) {
                init++;
            } else if(val > 0) {
                positive.add(val);
            } else {
                notPositive.add(val);
            }
        }
        notPositive.sort(Comparator.naturalOrder());
        positive.sort(Comparator.reverseOrder());
        return init;
    }

    static int calcSum(List<Integer> list) {
        if(list.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for(int i = 1; i<list.size(); i+=2) {
            sum += list.get(i-1)*list.get(i);
        }
        if(list.size()%2 == 1) {
            sum += list.get(list.size()-1);
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        int result = setInput();
        result += calcSum(notPositive);
        result += calcSum(positive);
        System.out.println(result);
    }
}
