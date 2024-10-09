package BOJ3425;

import java.io.*;
import java.util.*;

public class Main {

    static final long MAX = 1_000_000_000;
    static int stackSize = 0;
    static int[] stack = new int[2000];
    static Queue<Integer> numQueue = new ArrayDeque<>();
    static List<String> odrderList = new ArrayList<>();
    
    static boolean setInput(BufferedReader br) throws IOException {
        odrderList.clear();
        numQueue.clear();
        String input;
        while(!(input = br.readLine()).equals("END")) {
            if(input.equals("QUIT")) {
                return false;
            }
            odrderList.add(input);
        }
        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i<n; i++) {
            numQueue.add(Integer.parseInt(br.readLine()));
        }
        br.readLine();

        return true;
    }

    static void clear() {
        stackSize = 0;
    }

    static void push(int val) {
        stack[stackSize++] = val;
    }

    static int pop() {
        return stack[--stackSize];
    }

    static void parseOrder(String order) {
        if(order.substring(0,3).equals("NUM")) {
            push(Integer.parseInt(order.substring(4)));
        } else {
            long result = 0;
            switch (order) {
                case "POP":
                    pop();
                    break;
            
                case "INV":
                    stack[stackSize-1] *= -1;
                    break;

                case "DUP":
                    push(stack[stackSize-1]);
                    break;

                case "SWP":
                    int tmp = stack[stackSize-1];
                    stack[stackSize-1] = stack[stackSize-2];
                    stack[stackSize-2] = tmp;
                    break;

                case "ADD":
                int a1 = pop();
                    int a2 = pop();
                    push(a1+a2);
                    result = a1+a2;
                    break;

                case "SUB":
                    int s1 = pop();
                    int s2 = pop();
                    push(s2-s1);
                    result = s2-s1;
                    break;

                case "MUL":
                    int m1 = pop();
                    int m2 = pop();
                    push(m1*m2);
                    result = (long)m1*(long)m2;
                    break;

                case "DIV":
                    int d1 = pop();
                    int d2 = pop();
                    result = Math.abs(d2)/Math.abs(d1);
                    if((d1<0&&d2>0)||(d1>0&&d2<0)) {
                        result = -result;
                    }
                    push((int)result);
                    break;

                case "MOD":
                    int mo1 = pop();
                    int mo2 = pop();
                    result = Math.abs(mo2)%Math.abs(mo1);
                    if(mo2<0) {
                        result = -result;
                    }
                    push((int)result);
                    break;

                default:
                    break;
            }
            if(Math.abs(result)>MAX) {
                throw new IllegalStateException();
            }
        }
    }

    static int calc(int num) {
        clear();

        push(num);
        for(String order : odrderList) {
            parseOrder(order);
        }
        if(stackSize>1) {
            throw new IllegalStateException();
        }
        return pop();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while(setInput(br)) {
            while(!numQueue.isEmpty()) {
                try {
                    bw.write(calc(numQueue.poll())+"\n");
                } catch (Exception e) {
                    bw.write("ERROR\n");
                }
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
