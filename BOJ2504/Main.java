package BOJ2504;

import java.io.*;
import java.util.*;

public class Main {

    static Deque<String> stack;
    
    static boolean isNumber(String s) {
        if(s.equals("(")||s.equals(")")||s.equals("[")||s.equals("]"))
            return false;
        return true;
    }

    static boolean checkPair(String target, int mul) {
        int sum = 0;
        while(!stack.isEmpty() && isNumber(stack.peekLast()))
            sum += Integer.parseInt(stack.pollLast());
        if(!stack.isEmpty() && stack.pollLast().equals(target))
            stack.addLast(String.valueOf(Math.max(mul*sum,mul)));
        else
            return false;
        return true;
    }

    static int solve(String brackets) {
        
        int ans = 0;
        for(int i = 0; i<brackets.length(); i++) {
            if (brackets.charAt(i)==')') {
                if(!checkPair("(",2))   return 0;
            } else if(brackets.charAt(i)==']') {
                if(!checkPair("[",3))   return 0;
            } else {
                stack.addLast(String.valueOf(brackets.charAt(i)));
            }
        }
        while(!stack.isEmpty()) {
            if(!isNumber(stack.peekLast())) return 0;
            ans += Integer.parseInt(stack.pollLast());
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        stack = new ArrayDeque<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(solve(br.readLine()));
    }
}