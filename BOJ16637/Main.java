package BOJ16637;

import java.io.*;
import java.util.*;

public class Main {
    
    static int N, opNum, ans;
    static boolean[] selected;
    static String[] equation;

    static int calc(int a, String op, int b) {
        switch (op) {
            case "+":
                a += b;
                break;

            case "-":
                a -= b;
                break;

            case "*":
                a *= b;
                break;

            default:
                break;
        }
        return a;
    }

    static int calcEquation() {
        Deque<String> dq = new ArrayDeque<>();
        for(int i = 0; i<equation.length; i++) {
            // 숫자인 경우 덱에 그냥 넣음
            if(i%2==0) {
                dq.add(equation[i]);
            } else {
                // 연산자인 경우 괄호안에 있으면 계산해서 값을 넣음
                if(selected[i/2]) {
                    int a = Integer.parseInt(dq.pollLast());
                    String op = equation[i];
                    int b = Integer.parseInt(equation[++i]);
                    dq.add(Integer.toString(calc(a, op, b)));
                }
                // 아닌경우 연산자를 그냥 넣음
                else {
                    dq.add(equation[i]);
                }
            }
        }
        int result = Integer.parseInt(dq.pollFirst());
        while(!dq.isEmpty()) {
            String op = dq.pollFirst();
            int n = Integer.parseInt(dq.pollFirst());
            result = calc(result, op, n);
        }
        return result;
    }

    static void DFS(int idx) {
        if(idx>=opNum) {
            ans = Math.max(ans, calcEquation());
            return;
        }

        // i번째 연산자에 괄호 미적용
        DFS(idx+1);
        // i번째 연산자에 괄호 적용
        selected[idx] = true;
        DFS(idx+2);
        selected[idx] = false;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        opNum = N/2;
        selected = new boolean[opNum];
        equation = br.readLine().split("");
        ans = Integer.MIN_VALUE;
        DFS(0);
        System.out.println(ans);
    }
}
