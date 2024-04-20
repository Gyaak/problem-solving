package SWEA.SWEA1244;

import java.io.*;
import java.util.*;

public class Solution {

    static class Node {
        int val, num;

        Node(int val, int num) {
            this.val = val;
            this.num = num;
        }
    }

    static int N, target, targetLen;
    static final int[] pow10 = {1, 10, 100, 1000, 10000, 100000};
    static boolean[] visited = new boolean[1000000];
    static Queue<Node> q = new ArrayDeque<>();
    
    static void setInput(BufferedReader br) throws Exception {

        Arrays.fill(visited, false);
        q.clear();

        StringTokenizer st = new StringTokenizer(br.readLine());
    	String tmp = st.nextToken();
        target = Integer.parseInt(tmp);
        targetLen = tmp.length();
        N = Integer.parseInt(st.nextToken());
    }
    
    static int swap(int n, int i, int j) {
        int val1 = (n/pow10[i])%10;
        int val2 = (n/pow10[j])%10;
        n += (val2-val1)*pow10[i];
        n += (val1-val2)*pow10[j];
        return n;
    }
    
    static int solve() {

        Node result = new Node(target, N);
        q.add(new Node(target, N));
        while(!q.isEmpty()) {
            int curVal = q.peek().val;
            int curNum = q.poll().num;
            if(visited[curVal] || curNum==0)
                continue;
            visited[curVal] = true;
            for(int i = 0; i<targetLen; i++) {
                for(int j = i+1; j<targetLen; j++) {
                    int tmpVal = swap(curVal, i, j);
                    if(!visited[tmpVal]) {
                        Node tmpNode = new Node(tmpVal, curNum-1);
                        if(tmpVal>result.val)
                            result = tmpNode;
                        q.add(tmpNode);
                    }
                }
            }
        }

        boolean hasSameDigit = false;
        boolean[] digit = new boolean[10];
        for(int i = 0; i<targetLen; i++) {
            int d = (result.val/pow10[i])%10;
            if(digit[d]) {
                hasSameDigit = true;
                break;
            } else {
                digit[d] = true;
            }
        }

        if(hasSameDigit) {
            return result.val;
        } else {
            return result.num%2==1?swap(result.val,0,1):result.val;
        }
    }
    
public static void main(String args[]) throws Exception
	{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
		int T=Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			setInput(br);
            bw.write("#"+test_case+" ");
            if(targetLen<2) bw.write(target+"\n");
            else            bw.write(solve()+"\n");
		}
        bw.flush();
        bw.close();
        br.close();
	}
}