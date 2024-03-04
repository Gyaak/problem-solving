package BOJ17471;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] p;
    static ArrayList<Integer>[] adj;

    static void setInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        p = new int[N];
        String[] inpuStrings = br.readLine().split(" ");
        for(int i = 0; i<N; i++)
            p[i] = Integer.parseInt(inpuStrings[i]);
        
        adj = new ArrayList[N];
        for(int i = 0; i<N; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int i = 0; i<N; i++) {
            inpuStrings = br.readLine().split(" ");
            int n = Integer.parseInt(inpuStrings[0]);
            for(int j = 1; j<=n; j++) {
                adj[i].add(Integer.parseInt(inpuStrings[j])-1);
            }
        }
        br.close();
    }

    static int BFS(int selection) {
        int visited = 0;
        int diff = Integer.MAX_VALUE;
        Queue<Integer> q = new ArrayDeque<>();

        int val0 = 0, val1 = 0;
        int st0 = 0, st1 = 0;
        for(int i = 0; i<N; i++) {
            if((selection&(1<<i))==0) {
                st0 = i;
                break;
            }
        }
        q.add(st0);
        val0 += p[st0];
        visited |= (1<<st0);
        while(!q.isEmpty()) {
            int cur = q.poll();
            for(int i : adj[cur]) {
                if((visited&(1<<i))==0 && (selection&(1<<i))==0) {
                    q.add(i);
                    val0 += p[i];
                    visited += (1<<i);
                }
            }
        }
        for(int i = 0; i<N; i++) {
            if((selection&(1<<i))!=0) {
                st1 = i;
                break;
            }
        }
        q.add(st1);
        val1 += p[st1];
        visited |= (1<<st1);
        while(!q.isEmpty()) {
            int cur = q.poll();
            for(int i : adj[cur]) {
                if((visited&(1<<i))==0 && (selection&(1<<i))!=0) {
                    q.add(i);
                    val1 += p[i];
                    visited += (1<<i);
                }
            }
        }
        
        if(visited==(1<<N)-1) {
            diff = Math.abs(val0-val1);
        }
        return diff;
    }

    public static void main(String[] args) throws IOException {
        setInput();
        int ans = Integer.MAX_VALUE;
        for(int i = 1; i<(1<<(N-1)); i++) {
            ans = Math.min(ans, BFS(i));
        }
        System.out.println(ans==Integer.MAX_VALUE?-1:ans);
    }
}
