package BOJ1725;

import java.io.*;
import java.util.*;

public class Main {

    static class Block {
        int height, num;

        Block(int height, int num) {
            this.height = height;
            this.num = num;
        }
    }

    static ArrayDeque<Block> stack = new ArrayDeque<>();

    static int solve(int h) {
        int val = 0;
        int num = 0;
        while(!stack.isEmpty() && stack.peekLast().height>=h) {
            Block tmpBlock = stack.pollLast();
            num += tmpBlock.num;
            val = Math.max(val, tmpBlock.height*num);
        }
        stack.addLast(new Block(h, num+1));
        return val;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int ans = 0;
        for(int i = 0; i<N; i++) {
            ans = Math.max(ans, solve(Integer.parseInt(br.readLine())));
        }
        System.out.println(Math.max(ans,solve(0)));
        br.close();
    }
}
