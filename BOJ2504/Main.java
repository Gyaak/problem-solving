package BOJ2504;

import java.io.*;
import java.util.*;

public class Main {

    // 스터디 시간에 말했던 방법 그대로 풀었습니다.
    
    // stack에는 왼쪽 괄호("(", "[")와 숫자만 들어감
    static Deque<String> stack;
    
    // 문자열 s가 왼쪽괄호면 false, 숫자면 true
    static boolean isNumber(String s) {
        if(s.equals("(")||s.equals(")")||s.equals("[")||s.equals("]"))
            return false;
        return true;
    }

    // 괄호의 짝이 맞는지 확인하는 함수
    // 괄호를 제대로 닫을 수 있으면 true 없으면 false
    static boolean checkPair(String target, int mul) {
        int sum = 0;
        // 스택에 숫자가 있는 경우 꺼내서 누적 합산
        while(!stack.isEmpty() && isNumber(stack.peekLast()))
            sum += Integer.parseInt(stack.pollLast());
        // 짝이 맞는 괄호가 있으면 sum을 2 또는 3만큼 곱해 스택에 다시 넣음
        // 없으면 false
        if(!stack.isEmpty() && stack.pollLast().equals(target))
            stack.addLast(String.valueOf(Math.max(mul*sum,mul)));
        else
            return false;
        return true;
    }

    static int solve(String brackets) {
        int ans = 0;
        for(int i = 0; i<brackets.length(); i++) {
            // 왼쪽 괄호는 무조건 스택에 집어넣음
            // 오른쪽 괄호는 짝을 맞출 수 있으면 pass
            // 짝이 맞지 않으면 올바른 괄호가 아니므로 0반환
            if (brackets.charAt(i)==')') {
                if(!checkPair("(",2))   return 0;
            } else if(brackets.charAt(i)==']') {
                if(!checkPair("[",3))   return 0;
            } else {
                stack.addLast(String.valueOf(brackets.charAt(i)));
            }
        }
        // 오른쪽 괄호를 전부 처리한 후 스택에 있는 숫자를 합산함
        // 이때 스택에 처리되지 않은 왼쪽 괄호가 있으면 올바른 괄호가 아니므로 0반환
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