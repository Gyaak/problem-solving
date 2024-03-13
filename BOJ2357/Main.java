package BOJ2357;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, treeSize;
    static int[] maxTree, minTree;

    static void setInput(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        while((1<<treeSize)<=N)
            treeSize++;
        treeSize = (1<<(treeSize+1));
        maxTree = new int[treeSize];
        minTree = new int[treeSize];
        Arrays.fill(maxTree,-1);
        Arrays.fill(minTree,-1);
        for(int i = treeSize/2; i<treeSize/2+N; i++) {
            int n = Integer.parseInt(br.readLine());
            maxTree[i] = n;
            minTree[i] = n;
        }
    }

    static int MaxTree(int n) {
        if(n>=treeSize)  return 0;
        if(maxTree[n]==-1) {
            maxTree[n] = Math.max(MaxTree(2*n),MaxTree(2*n+1));
        }
        return maxTree[n];
    }

    static int MinTree(int n) {
        if(n>=treeSize)  return Integer.MAX_VALUE;
        if(minTree[n]==-1) {
            minTree[n] = Math.min(MinTree(2*n),MinTree(2*n+1));
            if(minTree[n]==-1)  minTree[n] = Integer.MAX_VALUE;
        }
        return minTree[n];
    }

    static int findMaxVal(int a, int b) {

        int l = (treeSize>>1) + a - 1;
        int leftVal = MaxTree(l);
        int leftParent = l>>1;
        int r = (treeSize>>1) + b - 1;
        int rightVal = MaxTree(r);
        int rightParent = r>>1;

        while(leftParent!=rightParent) {
            if(l!=2*leftParent+1)
                leftVal = Math.max(leftVal, MaxTree(2*leftParent+1));
            if(r!=2*rightParent)
                rightVal = Math.max(rightVal, MaxTree(2*rightParent));
            l = leftParent;
            r = rightParent;
            leftParent >>= 1;
            rightParent >>= 1;
            
        }

        return Math.max(leftVal, rightVal);
    }
    
    static int findMinVal(int a, int b) {

        int l = (treeSize>>1) + a - 1;
        int leftVal = MinTree(l);
        int leftParent = l>>1;
        int r = (treeSize>>1) + b - 1;
        int rightVal = MinTree(r);
        int rightParent = r>>1;
        
        while(leftParent!=rightParent) {
            if(l!=2*leftParent+1)
                leftVal = Math.min(leftVal, MinTree(2*leftParent+1));
            if(r!=2*rightParent)
                rightVal = Math.min(rightVal, MinTree(2*rightParent));
            l = leftParent;
            r = rightParent;
            leftParent >>= 1;
            rightParent >>= 1;
        }

        return Math.min(leftVal, rightVal);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        setInput(br);
        for(int i = 0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            bw.write(findMinVal(a, b) + " " + findMaxVal(a, b) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
